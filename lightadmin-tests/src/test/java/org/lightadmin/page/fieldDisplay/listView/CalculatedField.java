package org.lightadmin.page.fieldDisplay.listView;

import org.junit.Before;
import org.junit.Test;
import org.lightadmin.SeleniumIntegrationTest;
import org.lightadmin.config.OrderTotalCalculationConfiguration;
import org.lightadmin.data.Domain;
import org.lightadmin.data.User;
import org.lightadmin.page.ListViewPage;
import org.lightadmin.page.LoginPage;
import org.springframework.beans.factory.annotation.Autowired;

import static org.lightadmin.util.DomainAsserts.assertTableRowData;

public class CalculatedField extends SeleniumIntegrationTest {

	@Autowired
	private LoginPage loginPage;

	private ListViewPage testOrderListPage;

	@Before
	public void setup() {
		removeAllDomainTypeAdministrationConfigurations();

		registerDomainTypeAdministrationConfiguration( OrderTotalCalculationConfiguration.class );

		testOrderListPage = loginPage.get().loginAs( User.ADMINISTRATOR ).navigateToDomain( Domain.TEST_ORDERS );
	}

	@Test
	public void displayingOrderTotal() {
		assertTableRowData( expectedResult2, testOrderListPage.getDataTable(), 3 );
	}

	private String[] expectedResult2 = { "3", "Order3: 3 line items", "226308.00" };
}
