package api.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeSuite;

public class BaseTest
{
  private final Logger logger = LogManager.getLogger();

  @BeforeSuite
  public void oneTimeSetup() {
    logger.debug("Test execution environment: {}", "Pet Store");
  }
}
