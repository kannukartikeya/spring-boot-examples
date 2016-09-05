package guru.springframework.bootstrap;

import guru.springframework.domain.Product;
import guru.springframework.repositories.ProductRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductLoader implements ApplicationListener<ContextRefreshedEvent> {

    private ProductRepository productRepository;

    private Logger log = Logger.getLogger(ProductLoader.class);

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Product mainLight = new Product();
        mainLight.setDescription("Main Light");
        mainLight.setPrice(new BigDecimal("1"));
        mainLight.setImageUrl("Main Light");
        mainLight.setProductId("235268845711068308");
        productRepository.save(mainLight);

        log.info("Saved mainLight - id: " + mainLight.getId());

        Product sideLight = new Product();
        sideLight.setDescription("Side Light");
        sideLight.setImageUrl("Side Light");
        sideLight.setProductId("168639393495335947");
        sideLight.setPrice(new BigDecimal("1"));
        productRepository.save(sideLight);

        log.info("Saved sideLight - id:" + sideLight.getId());
    }
}
