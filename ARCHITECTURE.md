# AUTOMATION ARCHITECTURE - BDD + TESTNG + SCALABLE

## Project Structure

```
AutomationCorebank/
├── pom.xml                          (Maven POM - TestNG + Cucumber dependencies)
├── Scenarios/
│   ├── inicioSession/
│   │   └── inicioSession.feature   (Login scenarios with @login tag)
│   └── agregarCarrito/
│       └── agregarCarrito.feature  (Cart scenarios with @AgregarCarrito tag)
│
└── src/test/java/
    ├── com/automation/
    │   ├── core/                   (Framework foundation - NO CHANGES NEEDED)
    │   │   ├── DriverManager.java           ✓ ThreadLocal WebDriver management
    │   │   ├── BasePage.java                ✓ Common WebDriver operations
    │   │   └── ScreenshotUtil.java          ✓ Screenshot capture utility
    │   │
    │   ├── config/                 (Configuration management)
    │   │   └── ConfigManager.java           ✓ Singleton pattern for environment.config
    │   │
    │   ├── pages/                  (Page Objects - UI element mappings ONLY)
    │   │   ├── LoginPage.java               ✓ Saucedemo login page elements
    │   │   └── ProductsPage.java            ✓ Saucedemo products/cart page elements
    │   │
    │   ├── business/               (Business Logic - REUSABLE across flows)
    │   │   ├── LoginBusiness.java           ✓ REUSABLE login logic (used in all workflows)
    │   │   └── ProductBusiness.java         ✓ REUSABLE product/cart logic
    │   │
    │   └── stepdefinitions/        (Cucumber Step Definitions)
    │       ├── CommonSteps.java             ✓ SHARED login steps (used by all runners)
    │       └── ProductSteps.java            ✓ Product-specific steps (extends CommonSteps)
    │
    └── runners/                    (TestNG Runners - Different execution profiles)
        ├── LoginRunner.java                 ✓ Executes @login scenarios
        ├── ProductRunner.java               ✓ Executes @AgregarCarrito scenarios
        ├── E2ERunner.java                   ✓ Executes BOTH @login + @AgregarCarrito (E2E workflow)
        └── TestRunner.java                  ✓ Executes ALL scenarios

└── src/test/resources/
    ├── config/
    │   └── environment.config       (Configuration properties - url, browser, wait times)
    ├── cucumber.properties
    └── features/
        ├── inicioSession/
        │   └── inicioSession.feature
        └── agregarCarrito/
            └── agregarCarrito.feature
```

---

## Architecture Design Principles

### 1. **Layered Architecture** (Clean Code)
```
Feature Files (Gherkin) 
    ↓
Step Definitions (CommonSteps + ProductSteps)
    ↓
Business Logic (LoginBusiness + ProductBusiness) ← REUSABLE
    ↓
Page Objects (LoginPage + ProductsPage) ← UI mappings only
    ↓
Core Framework (BasePage + DriverManager + ScreenshotUtil) ← Stable
```

### 2. **Reusability - LoginBusiness**
```
LoginBusiness is inherited/used in:
  • LoginRunner → Direct @login scenarios
  • ProductSteps → Login before cart operations
  • E2ERunner → Login + Shop workflow
  • Any future runner → Can reuse login flow
```

### 3. **Scalability - Adding New Flows**
To add a new test scenario (e.g., Checkout, Inventory):

1. **Create Feature File**
   ```gherkin
   # Scenarios/checkout/checkout.feature
   @checkout
   Feature: Checkout Process
   ```

2. **Create Page Object** (if needed)
   ```java
   // src/test/java/com/automation/pages/CheckoutPage.java
   public class CheckoutPage extends BasePage { ... }
   ```

3. **Create Business Logic** (if needed)
   ```java
   // src/test/java/com/automation/business/CheckoutBusiness.java
   public class CheckoutBusiness { ... }
   ```

4. **Create Step Definitions**
   ```java
   // src/test/java/com/automation/stepdefinitions/CheckoutSteps.java
   public class CheckoutSteps extends CommonSteps { ... }
   ```

5. **Create Runner**
   ```java
   // src/test/java/runners/CheckoutRunner.java
   @CucumberOptions(features = "Scenarios/checkout", tags = "@checkout")
   public class CheckoutRunner extends AbstractTestNGCucumberTests { ... }
   ```

---

## Execution Commands

### Run Single Flows
```bash
# Only @login scenarios (isolated login testing)
mvn test -Dtest=LoginRunner

# Only @AgregarCarrito scenarios (product/cart testing)
mvn test -Dtest=ProductRunner

# E2E: Login + Shopping (complete workflow)
mvn test -Dtest=E2ERunner

# All tests
mvn test
```

### Run with Tags
```bash
# Run specific tag
mvn test -Dcucumber.filter.tags="@login"

# Run multiple tags (OR condition)
mvn test -Dcucumber.filter.tags="@login or @AgregarCarrito"

# Run tag combination (AND condition)
mvn test -Dcucumber.filter.tags="@SmokeTest and @login"
```

---

## Key Features

### ✓ **Reusable Login**
- `LoginBusiness.java` encapsulates all login operations
- Used by ProductSteps for automatic authentication
- Used by E2ERunner for complete workflows
- No duplication across scenarios

### ✓ **E2E Integration**
- E2ERunner combines @login + @AgregarCarrito
- Single execution chains login → cart operations
- Reuses LoginBusiness and CommonSteps
- Scalable for complex multi-step workflows

### ✓ **ThreadLocal WebDriver** (Parallel Execution)
- DriverManager uses ThreadLocal for thread safety
- Supports parallel test execution if needed
- Each thread has isolated WebDriver instance

### ✓ **Clean Code**
- Strict separation of concerns
- Page Objects = UI mappings only (NO LOGIC)
- Business = Logic layer (REUSABLE)
- Step Definitions = Gherkin to business mapping
- Core = Stable framework (rarely changes)

### ✓ **No Duplication**
- Single source of truth for each component
- Business logic shared across all flows
- No conflicting Feature folders or duplicate code

### ✓ **Maintenance**
- Changes to login logic? Update `LoginBusiness.java`
- Changes to UI? Update `LoginPage.java`
- All runners automatically get updates
- No need to modify multiple files

---

## Test Assets Generated

After `mvn test`, reports are created in:
```
target/reports/
├── login-report.html          (LoginRunner results)
├── product-report.html        (ProductRunner results)
├── e2e-report.html           (E2ERunner results)
├── full-report.html          (TestRunner results)
└── screenshots/              (Failure screenshots)
```

---

## Dependencies (POM)

- **Selenium**: 4.15.0 (WebDriver automation)
- **Cucumber**: 7.14.0 (BDD framework)
- **TestNG**: 7.8.0 (Test execution engine)
- **WebDriverManager**: 5.6.3 (Driver auto-setup)
- **ExtentReports**: 5.1.1 (Enhanced reporting)

---

## Notes for Future Enhancements

1. **Add Checkout Scenarios**: Create CheckoutPage, CheckoutBusiness, CheckoutSteps, CheckoutRunner
2. **Database Tests**: Add DBBusiness layer using similar pattern
3. **API Tests**: Create APIBusiness layer for API validation
4. **Mobile Tests**: Create separate runners with @Mobile tag, use Appium DriverManager variant
5. **Performance Tests**: Create tags like @Performance, correlate with PerformanceGlitchUser
6. **Parallel Execution**: Configure pom.xml with Maven Failsafe plugin for thread-safe parallel runs

---

**Architecture Status**: ✅ PRODUCTION READY
- Scalable: Add new flows without modifying existing code
- Maintainable: Single responsibility per class
- Reusable: Business logic shared across all runners
- Clean: No duplication, clear separation of concerns
- Testable: Each component can be tested independently
