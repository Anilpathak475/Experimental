package com.bitnudge.ime.moneygram.keyViews;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.bitnudge.ime.moneygram.R;
import com.bitnudge.ime.moneygram.adapter.TransactionAdapter;
import com.bitnudge.ime.moneygram.core.CustomIME;
import com.bitnudge.ime.moneygram.core.CustomViewManager;
import com.bitnudge.ime.moneygram.interfaces.KeyView;
import com.bitnudge.ime.moneygram.interfaces.TransactionInterface;
import com.bitnudge.ime.moneygram.libs.CalenderUtil;
import com.bitnudge.ime.moneygram.libs.Util;
import com.bitnudge.ime.moneygram.model.Sample;
import com.bitnudge.ime.moneygram.model.Transaction;
import com.bitnudge.ime.moneygram.model.TransactionDetailed;
import com.bitnudge.ime.moneygram.store.TransactionStore;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class TransactionView implements KeyView, TransactionAdapter.ClickListener {
    private String TAG = this.getClass().getSimpleName();

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.edt_search)
    EditText edtSearch;

    @BindView(R.id.spn_status)
    Spinner spnStatus;

    @BindView(R.id.img_back)
    ImageView imgBack;

    private Unbinder unbinder;
    private CustomIME mCustomIme;
    private CustomViewManager customViewManager;
    private boolean inProgress;
    private View v;
    private List<TransactionDetailed> sortedTransaction = new ArrayList<>();

    private TransactionView(CustomViewManager customViewManager) {
        this.mCustomIme = customViewManager.getContext();
        this.customViewManager = customViewManager;

        LayoutInflater layoutInflater = LayoutInflater.from(this.mCustomIme);
        v = layoutInflater.inflate(R.layout.layout_transaction, null);
        unbinder = ButterKnife.bind(this, v);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.mCustomIme));


        List<String> status = new ArrayList<>();
        status.add("Pending");
        status.add("Failed");
        status.add("Successful");
        spnStatus.setAdapter(new ArrayAdapter<String>(mCustomIme, android.R.layout.simple_dropdown_item_1line, status));

        inProgress = false;
        spnStatus.setEnabled(false);
        loadJSONFromAsset();
    }

    public static TransactionView getInstance(CustomViewManager customViewManager) {
        return new TransactionView(customViewManager);
    }

    private void getHistory() {
        TransactionStore.getInstance().getTransactionHistory(customViewManager.getUserDetails().getAccesTokenInfo().getAccess_token(), new TransactionInterface() {
            @Override
            public void onSuccess(List<Transaction> transactions) {
                setAdapter(transactions);
            }

            @Override
            public void onFailure(String error) {

            }
        });
    }

    private void setAdapter(List<Transaction> transactions) {
        recyclerView.setAdapter(new TransactionAdapter(sortTransaction(transactions), this));
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

    @OnClick(R.id.img_back)
    void onClickBack() {
        if (inProgress) return;
        inProgress = true;

        Util.makeTapSound(mCustomIme);
        customViewManager.showMenuView();
    }


    @Override
    public void onClick(Transaction transaction) {
        if (inProgress) return;
        inProgress = true;

        Util.makeTapSound(mCustomIme);
        customViewManager.showPaymentDetailsView(transaction);
    }

    private List<TransactionDetailed> sortTransaction(List<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            Date transactionDate = CalenderUtil.getDateFromString(transaction.getDateInitiated());
            if (transactionDate != null && CalenderUtil.getDifferenceBetweenDates(Calendar.getInstance().getTime(), transactionDate)< 7) {
                if (isObjectPresent("This Week")) {
                    List<Transaction> transactionList = sortedTransaction.get(0).getTransactions();
                    transactionList.add(transaction);
                    sortedTransaction.get(0).setTransactions(transactionList);
                } else {
                    sortedTransaction.add(0, getObject("This Week", transaction));
                }
            } else if (transactionDate != null && CalenderUtil.getDifferenceBetweenDates(Calendar.getInstance().getTime(), transactionDate) > 7 && CalenderUtil.getDifferenceBetweenDates(Calendar.getInstance().getTime(), transactionDate) < 14) {
                if (isObjectPresent("Last Week")) {
                    List<Transaction> transactionList = sortedTransaction.get(1).getTransactions();
                    transactionList.add(transaction);
                    sortedTransaction.get(1).setTransactions(transactionList);
                } else {
                    sortedTransaction.add(1, getObject("Last Week", transaction));

                }
            } else if (transactionDate != null && CalenderUtil.getDifferenceBetweenDates(Calendar.getInstance().getTime(), transactionDate) > 14) {
                if (isObjectPresent("Last Month")) {
                    List<Transaction> transactionList = sortedTransaction.get(2).getTransactions();
                    transactionList.add(transaction);
                    sortedTransaction.get(2).setTransactions(transactionList);

                } else {
                    sortedTransaction.add(2, getObject("Last Month", transaction));
                }
            }
        }
        return sortedTransaction;
    }

    private boolean isObjectPresent(String text) {
        if (sortedTransaction.size() > 0) {
            for (TransactionDetailed transactionDetailed : sortedTransaction) {
                if (transactionDetailed.getDuration().equalsIgnoreCase(text)) {
                    return true;
                }
            }
        }
        return false;
    }

    private TransactionDetailed getObject(String text, Transaction transaction) {
        TransactionDetailed transactionDetailed = new TransactionDetailed();
        transactionDetailed.setDuration(text);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        transactionDetailed.setTransactions(transactions);
        return transactionDetailed;
    }

    private void loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = customViewManager.getContext().getAssets().open("scratch.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            Gson gson = new Gson();
            Sample sample = gson.fromJson(json, Sample.class);
            List<Transaction> transaction = sample.getTransactions();
            setAdapter(transaction);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
