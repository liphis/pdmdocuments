package de.abas.pdmdocuments.infosystem.webservices;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import de.abas.pdmdocuments.infosystem.webservices.coffee.RestServiceCoffee;

public class AbstractRestServiceTest {

	private final String PASSWORD_DEFAULT = "password";
	private final String PASSWORD_NEW = "passwordnew";

	private final String USER_DEFAULT = "user";
	private final String USER_NEW = "usernew";

	private final String SERVER_DEFAULT = "server";
	private final String SERVER_NEW = "servernew";
	
	private  RestServiceCoffee restcoffee = new RestServiceCoffee(SERVER_DEFAULT, USER_DEFAULT, PASSWORD_DEFAULT);


//	@Rule
//	public final WireMockRule serviceMock = new WireMockRule(
	// wireMockConfig().httpsPort(HTTPS_PORT).trustStorePath(getTrustAndKeystorePath())
	// .keystorePath(getTrustAndKeystorePath()).port(HTTP_PORT).extensions(new
	// GZipTransformer()));

	/*
	@Test
	public void testSetPasword() {
		restcoffee.setPasword(PASSWORD_NEW);
		assertEquals(PASSWORD_NEW, restcoffee.password);
	}

	@Test
	public void testSetUser() {
		restcoffee.setUser(USER_NEW);
		assertEquals(USER_NEW, restcoffee.user);
	}

	@Test
	public void testSetServer() {
		restcoffee.setServer(SERVER_NEW);
		assertEquals(SERVER_NEW, restcoffee.server);
	}
 */
//	@Test
//	public void testCallRestservice() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testDownloadFileFromRestservice() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetFilesforPDMDocs() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testFilterPdmDocs() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetTargetPath() {
//		fail("Not yet implemented");
//	}

//	private void stubService() throws IOException {
//		serviceMock.stubFor(get(urlPathEqualTo(REQUEST_URL))
//				.willReturn(aResponse().withStatus(200).withHeader("Content-Type", MediaType.APPLICATION_JSON)
//						.withBody(getServiceResponseContent("sample-response.json"))));
//	}

}
