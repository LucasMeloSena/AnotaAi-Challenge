package br.dev.lucasena.anota_ai_challenge.domain.useCases.aws;

import br.dev.lucasena.anota_ai_challenge.domain.dtos.AwsMessageDTO;
import br.dev.lucasena.anota_ai_challenge.domain.models.AwsNotification;
import br.dev.lucasena.anota_ai_challenge.domain.models.Product;
import br.dev.lucasena.anota_ai_challenge.domain.useCases.product.GenerateCatalogJsonUseCase;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.Topic;
import com.google.gson.Gson;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AwsSnsUseCase {
    @Autowired
    private AmazonSNS snsClient;

    @Autowired
    @Qualifier("catalogEventsTopic")
    private Topic catalogTopic;

    @Autowired
    private GenerateCatalogJsonUseCase generateCatalog;

    private final Gson gson;

    public AwsSnsUseCase() {
        this.gson = new Gson();
    }

    public void publish(AwsMessageDTO message) {
        this.snsClient.publish(catalogTopic.getTopicArn(), message.message());
    }

    @SqsListener("${aws.sqs.queue.name}")
    public void consume(String message)  {
        AwsNotification notification = gson.fromJson(message, AwsNotification.class);
        Product product = gson.fromJson(notification.getMessage(), Product.class);
        this.generateCatalog.execute(product.getOwnerId());
    }
}
