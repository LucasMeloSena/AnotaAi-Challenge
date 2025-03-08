package br.dev.lucasena.anota_ai_challenge.domain.models;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AwsNotification {
    @SerializedName("Type")
    private String type;

    @SerializedName("MessageId")
    private String messageId;

    @SerializedName("TopicArn")
    private String topicArn;

    @SerializedName("Message")
    private String message;

    @SerializedName("Timestamp")
    private String timestamp;
}
