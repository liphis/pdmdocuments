package de.abas.pdmdocuments.coffee.infosystem.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import de.abas.erp.db.schema.userenums.UserEnumPdmSystems;
import de.abas.pdmdocuments.coffee.infosystem.PdmDocumentsException;
import de.abas.pdmdocuments.coffee.infosystem.utils.Util;

public class ConfigurationHandler {

	protected static final Logger log = Logger.getLogger(ConfigurationHandler.class);
	private static final String CONFIGFILE = "owpdm/pdmDocuments.config.properties";
	private static final String PDM_CONFIG_SERVER = "pdm.config.server";
	private static final String PDM_CONFIG_USER = "pdm.config.user";
	private static final String PDM_CONFIG_PASSWORD = "pdm.config.password";
	private static final String PDM_CONFIG_TENANT = "pdm.config.tenant";
	private static final String PDM_CONFIG_FIELDFORPARTNUMBER = "pdm.config.fieldforpartnumber";
	private static final String PDM_CONFIG_FIELDFORPARTPROFILEID = "pdm.config.fieldforpartProFileID";
	private static final String PDM_CONFIG_PDMSYSTEM = "pdm.config.pdmsystem";
	private static final String PDM_CONFIG_SQL_SERVER = "pdm.config.sqlserver.server";
	private static final String PDM_CONFIG_SQL_PORT = "pdm.config.sqlserver.port";
	private static final String PDM_CONFIG_SQL_DATABASE = "pdm.config.sqlserver.database";
	private static final String PDM_CONFIG_SQL_USER = "pdm.config.sqlserver.user";
	private static final String PDM_CONFIG_SQL_PASSWORD = "pdm.config.sqlserver.password";
	private static final String PDM_CONFIG_SQL_DRIVER = "pdm.config.sqlsever.driver";
	private static final String PDM_CONFIG_FILETYPES_EMAIL = "pdm.config.filetypes.email";
	private static final String PDM_CONFIG_FILETYPES_PRINTER = "pdm.config.filetypes.printer";
	private static final String PDM_CONFIG_FILETYPES_SCREEN = "pdm.config.filetypes.screen";
	private static final String PDM_CONFIG_DOKART = "pdm.config.dokart";

	private ConfigurationHandler() {
		throw new IllegalStateException("Utility class");
	}

	public static Configuration loadConfiguration() throws PdmDocumentsException {

		File propertiesFile = new File(CONFIGFILE);
		Configuration config = Configuration.getInstance();
		if (propertiesFile.exists()) {

			try (FileInputStream in = new FileInputStream(propertiesFile);) {

				Properties configProperties = new Properties();
				configProperties.load(in);

				// config contains all properties read from the file
				String restServer = configProperties.getProperty(PDM_CONFIG_SERVER);
				String restUser = configProperties.getProperty(PDM_CONFIG_USER);
				String restPassword = configProperties.getProperty(PDM_CONFIG_PASSWORD);
				String restTenant = configProperties.getProperty(PDM_CONFIG_TENANT);
				String pdmSystemString = configProperties.getProperty(PDM_CONFIG_PDMSYSTEM);

				UserEnumPdmSystems pdmSystem = UserEnumPdmSystems.valueOf(pdmSystemString);

				if (pdmSystem == null) {
					throw new PdmDocumentsException(
							Util.getMessage("pdmDocument.error.pdmsystemnull", pdmSystemString));
				}

				String sqlServer = configProperties.getProperty(PDM_CONFIG_SQL_SERVER);
				String sqlPortString = configProperties.getProperty(PDM_CONFIG_SQL_PORT);
				String sqldatabase = configProperties.getProperty(PDM_CONFIG_SQL_DATABASE);
				String sqldriver = configProperties.getProperty(PDM_CONFIG_SQL_DRIVER);

				Integer sqlPort = null;
				if (sqlPortString != null && !sqlPortString.isEmpty()) {
					sqlPort = new Integer(sqlPortString);
				}
				String sqlUser = configProperties.getProperty(PDM_CONFIG_SQL_USER);
				String sqlPassword = configProperties.getProperty(PDM_CONFIG_SQL_PASSWORD);

				String fileTypesEmail = configProperties.getProperty(PDM_CONFIG_FILETYPES_EMAIL);
				String fileTypesPrinter = configProperties.getProperty(PDM_CONFIG_FILETYPES_PRINTER);
				String fileTypesScreen = configProperties.getProperty(PDM_CONFIG_FILETYPES_SCREEN);
				String fieldForPartNumber = configProperties.getProperty(PDM_CONFIG_FIELDFORPARTNUMBER);
				String fieldforPartProFileID = configProperties.getProperty(PDM_CONFIG_FIELDFORPARTPROFILEID);
				String dokart = configProperties.getProperty(PDM_CONFIG_DOKART);

				config.initConfiguration(restServer, restUser, restPassword, restTenant, fieldForPartNumber,
						fieldforPartProFileID, pdmSystem, sqlServer, sqlPort, sqldatabase, sqldriver, sqlUser,
						sqlPassword, fileTypesEmail, fileTypesPrinter, fileTypesScreen, dokart);

			} catch (IOException e) {
				throw new PdmDocumentsException(Util.getMessage("pdmDocument.error.loadKonfiguration"), e);
			} catch (NumberFormatException e) {
				throw new PdmDocumentsException(Util.getMessage("pdmDocument.error.sqlport"), e);
			}

		} else {
			throw new PdmDocumentsException(Util.getMessage("pdmDocument.error.loadKonfiguration.noFile"));
		}

		return config;

	}

	public static void saveConfigurationtoFile(Configuration config) throws PdmDocumentsException {

		File propertiesFile = new File(CONFIGFILE);

		Properties configProperties = new Properties();

		configProperties.setProperty(PDM_CONFIG_SERVER, config.getRestServer());
		configProperties.setProperty(PDM_CONFIG_USER, config.getRestUser());
		configProperties.setProperty(PDM_CONFIG_PASSWORD, config.getRestPassword());
		configProperties.setProperty(PDM_CONFIG_TENANT, config.getRestTenant());

		configProperties.setProperty(PDM_CONFIG_PDMSYSTEM, config.getPdmSystem().name());

		configProperties.setProperty(PDM_CONFIG_SQL_SERVER, config.getSqlServer());
		configProperties.setProperty(PDM_CONFIG_SQL_PORT, config.getSqlPortString());
		configProperties.setProperty(PDM_CONFIG_SQL_DATABASE, config.getSqldatabase());
		configProperties.setProperty(PDM_CONFIG_SQL_USER, config.getSqlUser());
		configProperties.setProperty(PDM_CONFIG_SQL_PASSWORD, config.getSqlPassword());
		configProperties.setProperty(PDM_CONFIG_SQL_DRIVER, config.getSqlDriver());

		configProperties.setProperty(PDM_CONFIG_FILETYPES_EMAIL, config.getFileTypesEmail());
		configProperties.setProperty(PDM_CONFIG_FILETYPES_PRINTER, config.getFileTypesPrinter());
		configProperties.setProperty(PDM_CONFIG_FILETYPES_SCREEN, config.getFileTypesScreen());
		configProperties.setProperty(PDM_CONFIG_FIELDFORPARTNUMBER, config.getPartFieldName());
		configProperties.setProperty(PDM_CONFIG_FIELDFORPARTPROFILEID, config.getPartProFileIDFieldName());

		configProperties.setProperty(PDM_CONFIG_DOKART, config.getDokart());

		FileOutputStream out;
		try {
			out = new FileOutputStream(propertiesFile);
			configProperties.store(out, "---config PDMDocuments---");
			out.close();
		} catch (FileNotFoundException e) {
			throw new PdmDocumentsException(Util.getMessage("pdmDocument.error.saveConfiguration"));
		} catch (IOException e) {
			throw new PdmDocumentsException(Util.getMessage("pdmDocument.error.saveConfiguration"));
		}

	}

}
