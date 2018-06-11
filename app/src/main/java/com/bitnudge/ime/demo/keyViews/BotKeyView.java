package com.bitnudge.ime.demo.keyViews;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

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

import java.text.MessageFormat;

/**
 * Created by Adhityan on 3/20/18.
 */

@SuppressLint("ViewConstructor")
public class BotKeyView implements KeyView, View.OnClickListener, MessageInput.InputListener, DialogFlow.AIInterface {
    private String TAG = this.getClass().getSimpleName();

    private CustomIME mCustomIme;
    private CustomViewManager customViewManager;
    private View v;

    private MessageInput chatMessage;
    private MessagesListAdapter<ChatMessage> adapter;
    private DialogFlow flow;

    private BotKeyView(CustomViewManager customViewManager) {
        this.mCustomIme = customViewManager.getContext();
        this.customViewManager = customViewManager;

        LayoutInflater layoutInflater = LayoutInflater.from(this.mCustomIme);
        v = layoutInflater.inflate(R.layout.bot_view_layout, null);

        flow = new DialogFlow(mCustomIme);
        chatMessage = v.findViewById(R.id.chat_message);
        chatMessage.getInputEditText().setOnClickListener(this);
        chatMessage.setInputListener(this);

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
        flow = null;
        chatMessage = null;
        adapter = null;
    }

    @Override
    public boolean onSubmit(CharSequence ch) {
        Util.makeTapSound(mCustomIme);

        String input = ch.toString();
        flow.executeRequest(input, this);
        adapter.addToStart(new ChatMessage(input, ChatAuthor.AuthorType.USER), true);
        return true;
    }

    @Override
    public void onClick(View view) {
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
        Toast.makeText(mCustomIme, MessageFormat.format("Failed {0}", message), Toast.LENGTH_LONG).show();
    }
}
