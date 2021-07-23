package com.formacionbdi.springboot.app.productos;

import com.formacionbdi.springboot.app.productos.SpringbootServicioProductosApplicationTests;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import java.io.StringReader;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import io.restassured.response.ResponseOptions;

import static org.springframework.cloud.contract.verifier.assertion.SpringCloudContractAssertions.assertThat;
import static org.springframework.cloud.contract.verifier.util.ContractVerifierUtil.*;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

@SuppressWarnings("rawtypes")
public class ProductsTest extends SpringbootServicioProductosApplicationTests {

	@Test
	public void validate_find_product_by_id() throws Exception {
		// given:
			MockMvcRequestSpecification request = given();


		// when:
			ResponseOptions response = given().spec(request)
					.get("/ver/1");

		// then:
			assertThat(response.statusCode()).isEqualTo(200);
			assertThat(response.header("Content-Type")).matches("application/xml.*");

		// and:
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			builderFactory.setNamespaceAware(true);
			DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
			Document parsedXml = documentBuilder.parse(new InputSource(new StringReader(response.getBody().asString())));
		// and:
			assertThat(valueFromXPath(parsedXml, "/Producto/id/text()")).isEqualTo("1");
			assertThat(valueFromXPath(parsedXml, "/Producto/nombre/text()")).isEqualTo("Nissan");
			assertThat(valueFromXPath(parsedXml, "/Producto/precio/text()")).isEqualTo("800.0");
			assertThat(valueFromXPath(parsedXml, "/Producto/createAt/text()")).isEqualTo("1394938800000");
	}

}
