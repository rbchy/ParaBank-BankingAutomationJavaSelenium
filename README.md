<div align="center">

# 🏦 ParaBank Banking Automation

### Java + Selenium + Cucumber suite for the ParaBank online-banking demo — accounts, transfers, bill pay, loans, profile

[![Typing SVG](https://readme-typing-svg.demolab.com/?font=Fira+Code&size=18&pause=1500&color=FFC107&center=true&vCenter=true&width=900&repeat=true&lines=Java+17+%C2%B7+Selenium+4+%C2%B7+Cucumber+7+%C2%B7+TestNG;Register+%C2%B7+Login+%C2%B7+Open+account+%C2%B7+Transfer+%C2%B7+Bill+pay+%C2%B7+Loan;20+%2F+20+scenarios+green+%C2%B7+3+demo+defects+tracked)](https://git.io/typing-svg)

<!-- ============ STATUS BAR ============ -->
[![Tests](https://github.com/rbchy/ParaBank-BankingAutomationJavaSelenium/actions/workflows/tests.yml/badge.svg)](https://github.com/rbchy/ParaBank-BankingAutomationJavaSelenium/actions/workflows/tests.yml)
![Pass Rate](https://img.shields.io/badge/Pass%20Rate-100%25%20(20%2F20)-22c55e?style=for-the-badge&logo=checkmarx&logoColor=white)
![Defects](https://img.shields.io/badge/Demo%20defects%20found-3-f59e0b?style=for-the-badge)
![Domain](https://img.shields.io/badge/Domain-Banking-155e75?style=for-the-badge)
![Target](https://img.shields.io/badge/Target-ParaBank%20by%20Parasoft-1e3a8a?style=for-the-badge&logo=googlechrome&logoColor=white)

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Selenium](https://img.shields.io/badge/Selenium-4.27-43B02A?style=for-the-badge&logo=selenium&logoColor=white)
![Cucumber](https://img.shields.io/badge/Cucumber-7.20-23D96C?style=for-the-badge&logo=cucumber&logoColor=white)
![TestNG](https://img.shields.io/badge/TestNG-7.10-FF7F00?style=for-the-badge)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)

![Last Commit](https://img.shields.io/github/last-commit/rbchy/ParaBank-BankingAutomationJavaSelenium?style=flat-square&color=0d9488&label=Last%20commit)
![Repo Size](https://img.shields.io/github/repo-size/rbchy/ParaBank-BankingAutomationJavaSelenium?style=flat-square&color=155e75&label=Repo%20size)

</div>

---

## 📌 At a Glance

<table>
<tr>
<td width="50%" valign="top">

**🙋 What this is**

A BDD suite for **[ParaBank](https://parabank.parasoft.com/parabank/)**, Parasoft's public online-banking demo. Gherkin scenarios drive Page Objects through Selenium across the core banking journeys: registration, login / logout, accounts overview, opening accounts, fund transfers, bill pay, loans, transaction search, contact info and *Forgot login info*.

Every run **registers its own customer**, so the checks never depend on accounts someone else changed on the shared demo. Balances are read before and after money moves and compared to the cent.

> *A test that cannot fail is not a test — and a test that finds a real bug says so.*

</td>
<td width="50%" valign="top">

**📍 Quick facts**

| | |
|---|---|
| 🧪 **Scenarios** | 20 in 11 feature files (+ 3 `@known-defect`) |
| ✅ **CI** | 20 / 20 · headless Chrome · ≈ 70 s of tests |
| 🐞 **Defects found** | 3 in the public demo — tracked in their own CI job |
| 🏷️ **Tags** | `@smoke` · `@regression` · `@negative` · `@known-defect` |
| 📊 **Reports** | Cucumber HTML · JSON · JUnit XML · Surefire |

</td>
</tr>
</table>

---

## ✅ Latest Results

| Feature | Scenarios | What is asserted | Result |
|---|---:|---|---|
| 📝 Registration | 3 | *Welcome &lt;username&gt;* · passwords that differ → *"Passwords did not match."* · taken username → *"This username already exists."* | ![passed](https://img.shields.io/badge/-3%20passed-22c55e?style=flat-square) |
| 🔐 Login | 2 | Valid login opens *Accounts Overview* · empty form → *"Please enter a username and password."* | ![passed](https://img.shields.io/badge/-2%20passed-22c55e?style=flat-square) |
| 🚪 Logout | 1 | Back on the home page, login form shown, *Log Out* gone | ![passed](https://img.shields.io/badge/-1%20passed-22c55e?style=flat-square) |
| 📋 Accounts Overview | 1 | Every account number is numeric · the *Total* row equals the sum of balances | ![passed](https://img.shields.io/badge/-1%20passed-22c55e?style=flat-square) |
| ➕ Open New Account | 2 | SAVINGS and CHECKING → *"Account Opened!"*, new number listed in the overview | ![passed](https://img.shields.io/badge/-2%20passed-22c55e?style=flat-square) |
| 💸 Fund Transfer | 1 | *"Transfer Complete!"* with amount and both accounts · source **−$25.00**, target **+$25.00** | ![passed](https://img.shields.io/badge/-1%20passed-22c55e?style=flat-square) |
| 🧾 Bill Pay | 2 | Confirmation shows payee and amount · mismatched account numbers rejected | ![passed](https://img.shields.io/badge/-2%20passed-22c55e?style=flat-square) |
| 🏠 Request Loan | 2 | Affordable loan → *Approved* + loan account · down payment above balance → *Denied* | ![passed](https://img.shields.io/badge/-2%20passed-22c55e?style=flat-square) |
| 🔎 Find Transactions | 2 | A transfer is found by its amount (*Funds Transfer Sent*) · date range returns results | ![passed](https://img.shields.io/badge/-2%20passed-22c55e?style=flat-square) |
| 👤 Update Contact Info | 2 | Form shows the registered details · empty first name → *"First name is required."* | ![passed](https://img.shields.io/badge/-2%20passed-22c55e?style=flat-square) |
| 🔑 Forgot Login Info | 2 | Name + address + SSN find the username · unknown SSN → *not found* | ![passed](https://img.shields.io/badge/-2%20passed-22c55e?style=flat-square) |
| **Total** | **20** | | **100 %** |

<sub>GitHub Actions, Ubuntu 24.04, Temurin 17, headless Chrome ([workflow](.github/workflows/tests.yml)).</sub>

---

## 🐞 Defects Found in the Public Demo

These scenarios are tagged `@known-defect`. They run on every push in a separate **non-blocking** CI job, so the day Parasoft fixes one, that job turns green.

| # | Scenario | Expected | What ParaBank does |
|---|---|---|---|
| 1 | Log in with an unknown username / wrong password | *"The username and password could not be verified."* | Logs the visitor in **as an existing customer** (first *John Smith*, later the most recently registered customer) |
| 2 | Update Contact Info → *Update Profile* | *"Profile Updated"* and the new values saved | The page sends `username=&password=` empty with the update; the server answers *"An internal error has occurred"* and nothing is saved |
| 3 | Bill Pay, then check the account | Balance lower by the amount, *Bill Payment to …* in the transactions | *"Bill Payment Complete"* is shown, but the balance is unchanged and no transaction is recorded |

Defect 1 was confirmed with plain HTTP calls from a fresh client (no cookies) as well as in the browser.

---

## 🏗️ Framework Architecture

```mermaid
flowchart LR
  F["Feature files<br/>11 features"] --> S["Step definitions<br/>Auth · Account · Payment · Profile"]
  S --> P["Page Objects<br/>Login · Register · Overview · OpenAccount<br/>Transfer · BillPay · RequestLoan<br/>FindTransactions · UpdateContactInfo · ForgotLogin"]
  P --> B["BasePage<br/>explicit waits · AJAX-aware selects"]
  B --> D["DriverFactory<br/>Chrome per scenario"]
  D --> Q["ParaBank demo"]
  C["TestContext<br/>run's customer · noted balances"] -.-> S
  H["Hooks"] -.->|"screenshot + page text<br/>on failure"| D
  S --> R["Cucumber HTML · JSON · JUnit"]
```

- **Own test customer** — the first scenario that needs a logged-in user registers a unique customer; later scenarios log in with it
- **Money checks to the cent** — balances are read with `BigDecimal` before and after a transfer and compared exactly
- **AJAX-aware waits** — ParaBank fills account lists and forms after the page loads; `BasePage` waits for options and values, and retries on stale elements — no `Thread.sleep`
- **Readable failures** — on failure the hook attaches a screenshot and prints the page text and the REST calls the page made, so a CI failure can be read straight from the log
- **Config, not code** — URL, timeout and headless in `config.properties`; any value can be overridden with `-D`

---

## 🛠️ Tech Stack

| Layer | Technology | Version |
|---|---|---|
| Language | Java | 17 |
| Browser automation | Selenium WebDriver (Selenium Manager) | 4.27.0 |
| BDD | Cucumber JVM | 7.20.1 |
| Runner | TestNG (`cucumber-testng`) | 7.10.2 |
| Build | Maven (Surefire, `testng.xml`) | 3.x |
| CI | GitHub Actions | — |

---

## 📁 Project Structure

```
ParaBank-BankingAutomationJavaSelenium/
├── .github/workflows/tests.yml          ← main suite + non-blocking @known-defect job
├── src/test/java/
│   ├── hooks/Hooks.java                 ← Chrome per scenario, failure screenshot + page text
│   ├── pages/                           ← BasePage + 10 page objects
│   ├── stepdefinitions/                 ← AuthSteps, AccountSteps, PaymentSteps, ProfileSteps
│   ├── runners/TestNGCucumberRunner.java
│   └── utils/                           ← DriverFactory, Config, Customer, TestContext
├── src/test/resources/
│   ├── config.properties
│   └── features/                        ← 11 feature files
├── testng.xml
└── pom.xml
```

---

## ⚙️ Getting Started

**Prerequisites:** JDK 17+ · Maven 3.8+ · Google Chrome

```bash
git clone https://github.com/rbchy/ParaBank-BankingAutomationJavaSelenium.git
cd ParaBank-BankingAutomationJavaSelenium

mvn clean test                                          # all scenarios except @known-defect
mvn clean test -Dheadless=true                          # what CI runs
mvn clean test -Dcucumber.filter.tags="@smoke"          # smoke only
mvn clean test -Dcucumber.filter.tags="@known-defect"   # reproduce the demo defects
```

| Report | Location |
|---|---|
| Cucumber HTML | `target/cucumber-report.html` |
| Cucumber JSON / JUnit XML | `target/cucumber.json` · `target/cucumber.xml` |
| Surefire | `target/surefire-reports/` |

---

## 🧹 Maintenance Log

| Change | Why |
|---|---|
| Added `pom.xml`, step definitions and page objects for every module | The repo had feature files and one page object but **no build file and no step definitions**, so nothing could compile or run here |
| Removed `LoginTest` and `ExcelUtils` | `LoginTest` used a `LoginPage` class and a `testdata/LoginData.xlsx` file that did not exist |
| Feature files rewritten for the real demo | They used fixed account numbers `12345` / `54321` that do not belong to the logged-in customer |
| Each run registers its own customer | The shared `john` / `demo` account is changed by everyone who uses the demo |
| Negative scenarios added | Wrong / empty login, password mismatch, duplicate username, account mismatch, denied loan, required first name, unknown SSN |
| Three demo defects tagged `@known-defect` | They fail because of ParaBank, not the tests — see *Defects Found* |
| GitHub Actions | Every push is checked; reports kept as artifacts |
| README describes only what exists | The old README described a Spring Boot + MySQL backend, an HTML/JS frontend, JUnit 5 and a `ranajitchowdhury` GitHub account — none of it is in this repo. The PDF report (*9 passed*) came from a local project whose step code was never committed |

---

## 📈 Roadmap

- [x] Runnable framework, real assertions, GitHub Actions
- [x] Demo defects reproduced and tracked in CI
- [ ] REST Assured checks against ParaBank's `services/bank` API (accounts, transfers)
- [ ] Cross-check UI balances against the API after every money move
- [ ] Firefox and Edge in a CI browser matrix
- [ ] Extent report with failure screenshots

---

## 🔗 Related Projects

| Project | Stack |
|---|---|
| 👥 [OrangeHRM BDD Automation](https://github.com/rbchy/OrangeHRM_Automation) | Java · Selenium · Cucumber · TestNG — CI green |
| 🏥 [OpenEMR Healthcare](https://github.com/rbchy/OpenEMR_Automation) | Java · Selenium · Cucumber · TestNG — CI green |
| 🧪 [QA Brains — Java + Selenium](https://github.com/rbchy/QaBrainsAutomationJavaSelenium) | Java · Selenium · Cucumber — CI green |
| ⭐ [RbcTcsWorld E-Commerce Suite](https://github.com/rbchy/RbcTcsWorld_ECommerceSuite) | Spring Boot · REST Assured · Selenium · Playwright · CI quality gates |

<sub>All projects: [github.com/rbchy](https://github.com/rbchy)</sub>

---

## 👤 Author

<div align="center">

**Ranajit Baran Chowdhury (RB Chowdhury)**
QA Automation Engineer / SDET · West Norriton, Pennsylvania, USA

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/rbchy/)
[![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/rbchy)
[![Website](https://img.shields.io/badge/Website-rbchy.github.io%2Frbchy-0d9488?style=for-the-badge&logo=githubpages&logoColor=white)](https://rbchy.github.io/rbchy/)
[![Portfolio](https://img.shields.io/badge/Portfolio-Website-blueviolet?style=for-the-badge&logo=wix&logoColor=white)](https://rbc6543.wixsite.com/rbc-portfolio)
[![Gmail](https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white)](mailto:chyranajit@gmail.com)

<sub>ParaBank is a public demo by Parasoft that is shared and reset regularly; the suite only uses customers it registers itself.</sub>

⭐ *If this project helps you, a star on the repo is appreciated.*

</div>
