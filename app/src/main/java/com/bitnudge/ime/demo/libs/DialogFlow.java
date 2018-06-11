package com.bitnudge.ime.demo.libs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import ai.api.AIServiceException;
import ai.api.android.AIConfiguration;
import ai.api.android.AIDataService;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;
import ai.api.model.Result;

public class DialogFlow {
    private AIDataService aiDataService;

    public DialogFlow(Context context) {
        final AIConfiguration config = new AIConfiguration("6985914de2c14f5c87fe8c5137326aa2",
                AIConfiguration.SupportedLanguages.English,
                AIConfiguration.RecognitionEngine.System);

        aiDataService = new AIDataService(context, config);
    }

    @SuppressLint("StaticFieldLeak")
    public void executeRequest(String input, final AIInterface i) {
        final AIRequest aiRequest = new AIRequest();
        aiRequest.setQuery(input);

        new AsyncTask<AIRequest, Void, AIResponse>() {
            @Override
            protected AIResponse doInBackground(AIRequest... requests) {
                final AIRequest request = requests[0];
                try {
                    return aiDataService.request(aiRequest);
                } catch (AIServiceException e) {
                    i.aiError(e.getMessage());
                }
                return null;
            }

            @Override
            protected void onPostExecute(AIResponse aiResponse) {
                if (aiResponse != null) {
                    final Result result = aiResponse.getResult();
                    final String speech = result.getFulfillment().getSpeech();
                    i.aiResponse(speech);
                }
            }
        }.execute(aiRequest);
    }

    public interface AIInterface {
        void aiResponse(String response);

        void aiError(String message);
    }
}
