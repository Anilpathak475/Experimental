package com.bitnudge.ime.demo.keyViews;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;

import com.bitnudge.ime.demo.R;
import com.bitnudge.ime.demo.core.CustomIME;
import com.bitnudge.ime.demo.core.CustomViewManager;
import com.bitnudge.ime.demo.interfaces.KeyView;
import com.bitnudge.ime.demo.libs.DialogFlow;
import com.bitnudge.ime.demo.libs.Util;
import com.bitnudge.ime.demo.model.ChatAuthor;
import com.bitnudge.ime.demo.model.ChatMessage;
import com.facebook.drawee.view.SimpleDraweeView;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import butterknife.Unbinder;

/**
 * Created by Adhityan on 3/20/18.
 */

@SuppressLint("ViewConstructor")
public class BotKeyView implements KeyView, MessageInput.InputListener, DialogFlow.AIInterface, View.OnClickListener, View.OnFocusChangeListener {
    private String TAG = this.getClass().getSimpleName();

    private CustomIME mCustomIme;
    private CustomViewManager customViewManager;
    private Unbinder unbinder;
    private View v;

    @BindView(R.id.chat_message)
    MessageInput chatMessage;

    private MessagesListAdapter<ChatMessage> adapter;
    private DialogFlow flow;

    private BotKeyView(CustomViewManager customViewManager) {
        this.mCustomIme = customViewManager.getContext();
        this.customViewManager = customViewManager;

        LayoutInflater layoutInflater = LayoutInflater.from(this.mCustomIme);
        v = layoutInflater.inflate(R.layout.bot_view_layout, null);
        unbinder = ButterKnife.bind(this, v);

        flow = new DialogFlow(mCustomIme);
        chatMessage.setInputListener(this);
        chatMessage.getInputEditText().setOnClickListener(this);
        chatMessage.getInputEditText().setOnFocusChangeListener(this);

        adapter = new MessagesListAdapter<>("1", new ImageLoader() {
            @Override
            public void loadImage(SimpleDraweeView imageView, String url) {
                imageView.setImageURI(Uri.parse(url));
            }
        });

        MessagesList chatContent = v.findViewById(R.id.chat_content);
        chatContent.setAdapter(adapter);
    }

    public static BotKeyView getInstance(CustomViewManager customViewManager) {
        return new BotKeyView(customViewManager);
    }

    @Override
    public View getView() {
        return v;
    }

    @Override
    public void destroy() {
        mCustomIme = null;
        customViewManager = null;
        unbinder.unbind();
    }

    @Override
    public boolean onSubmit(CharSequence ch) {
        Util.makeTapSound(mCustomIme);

        String input = ch.toString();
        flow.executeRequest(input, this);
        adapter.addToStart(new ChatMessage(input, ChatAuthor.AuthorType.USER), true);
        return true;
    }

    @OnFocusChange(R.id.chat_message)
    public void OnFocus(MessageInput m, boolean hasFocus) {
        if(hasFocus) {
            View view = m.getInputEditText();
            getFocus(view);
        }
    }

    private void getFocus(View view) {
        Util.makeTapSound(mCustomIme);

        try {
            view.findFocus();
            view.requestFocus();
            mCustomIme.setInputTarget(chatMessage.getInputEditText());
        } catch (Exception e) {
            Util.logException(TAG, "onClick", e);
        }
    }

    @Override
    public void aiResponse(String response) {
        adapter.addToStart(new ChatMessage(response, ChatAuthor.AuthorType.BOT), true);
    }

    @Override
    public void aiError(String message) {
        (new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message message) {
                adapter.addToStart(new ChatMessage("Oh, looks like you don't have internet atm! Try again later?", ChatAuthor.AuthorType.BOT), true);
                //Toast.makeText(mCustomIme, MessageFormat.format("Failed {0}", message), Toast.LENGTH_LONG).show();
            }
        }).sendEmptyMessage(0);

    }

    @Override
    public void onClick(View view) {
        getFocus(view);
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        if(hasFocus) getFocus(view);
    }
}
