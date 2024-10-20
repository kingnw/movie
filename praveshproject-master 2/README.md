# NIT3213 Android Project
Login Credentials:
username= pravesh
password= s4677960

This project is an Android application for NIT3213 Assignment that integrates with an API for authentication and displays a list of entities with details.

## Features

- **Login Functionality**: Users can log in using a username and password, which fetches a `keypass` from the API.
- **Dashboard**: After a successful login, the app displays a list of entities retrieved from the API.
- **Details Page**: Clicking on an entity in the dashboard shows its details.
- **Profile Icon**: The dashboard includes a profile image next to each entity.
- **Unit Testing**: Simple tests are included for login validation and API interactions.

## API Endpoints

The app interacts with the following API endpoints:

1. **Login Endpoint** (`/footscray/auth`): Accepts a username and password, returning a `keypass`.
2. **Dashboard Endpoint** (`/dashboard/{keypass}`): Fetches a list of entities using the `keypass`.

## Getting Started

### Prerequisites

- **Android Studio**: Make sure you have Android Studio installed.
- **API Access**: Ensure that the API endpoint `https://nit3213-api-h2b3-latest.onrender.com` is accessible.

### Installation

Sure! Here’s a clearer version of the installation instructions:

### Installation Instructions

1. **Clone the Repository**: Go to the repository at [https://github.com/pravesh307/praveshproject.git](https://github.com/pravesh307/praveshproject.git) and clone it.

2. **Download as Zip**:
   - Look for the green "Code" button on the repository page.
   - Click it, then select "Download ZIP" from the dropdown menu.

3. **Extract the Zip File**:
   - Locate the downloaded file named `praveshproject-master.zip`.
   - Extract the contents of the zip file.

4. **Open the Project**:
   - You should see a folder named `praveshproject-master` after extraction.
   - Open **Android Studio** and choose **Open an existing Android Studio project**.
   - Select the entire `praveshproject-master` folder (do not open individual files or subfolders).

5. **Sync Gradle**:
   - Once the project is open, Android Studio may prompt you to sync Gradle. Click on the "Sync Now" button when prompted.

6. **Run the App**:
   - You can now run the app on either an emulator or a physical device connected to your computer.

Feel free to reach out if you have any questions!

   Running the App
#Login:
Enter your username and password in the login screen.
Upon successful login, you will be redirected to the Dashboard.
#Dashboard:
The dashboard displays a list of entities retrieved from the API. Each item includes a profile image and information about the entity.
#Entity Details:
Clicking on an entity takes you to the details page, showing more information about the entity (name, culture, domain, etc.).

#Unit Testing
This project includes basic unit tests for login and API functionality. To run the tests:

- Go to app/src/test/java/com/example/nit3213project/.
- Right-click on the test files (e.g., LoginActivityTest.kt or DashboardActivityTest.kt).
- Select Run 'LoginActivityTest' or Run 'All Tests' to execute the tests.
   
