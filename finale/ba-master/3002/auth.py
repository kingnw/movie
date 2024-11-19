from flask import Blueprint, render_template, redirect, url_for, request, flash  # Import necessary Flask utilities
from flask_login import login_user, logout_user, login_required  # Import Flask-Login utilities
from models import db, User  # Import database and User model

# Define a blueprint for authentication-related routes
auth_blueprint = Blueprint('auth', __name__)

@auth_blueprint.route('/login', methods=['GET', 'POST'])
def login():
    """Handle user login."""
    if request.method == 'POST':  # Check if the request is a POST (form submission)
        username = request.form.get('username')  # Get the username from the form
        password = request.form.get('password')  # Get the password from the form

        # Validate missing fields
        if not username:
            flash("Username is required.", "auth-danger")  # Flash an error message for missing username
            return render_template('login.html')  # Reload the login form
        if not password:
            flash("Password is required.", "auth-danger")  # Flash an error message for missing password
            return render_template('login.html')  # Reload the login form

        # Validate user credentials
        user = User.get(username)  # Fetch the user by username from the database
        if user and user.check_password(password):  # Check if user exists and the password matches
            login_user(user)  # Log in the user
            flash("Logged in successfully.", "auth-success")  # Flash a success message
            return redirect(url_for('index'))  # Redirect to the homepage
        else:
            flash("Invalid username or password.", "auth-danger")  # Flash an error message for invalid credentials
            return render_template('login.html')  # Reload the login form with an error message

    return render_template('login.html')  # Render the login form for GET requests

@auth_blueprint.route('/logout')
@login_required  # Ensure that only logged-in users can access this route
def logout():
    """Handle user logout."""
    logout_user()  # Log out the current user
    flash("You have been logged out.", "auth-info")  # Flash a logout message
    return redirect(url_for('index'))  # Redirect to the homepage

@auth_blueprint.route('/register', methods=['GET', 'POST'])
def register():
    """Handle user registration."""
    if request.method == 'POST':  # Check if the request is a POST (form submission)
        username = request.form.get('username')  # Get the username from the form
        password = request.form.get('password')  # Get the password from the form

        # Validate missing fields
        if not username:
            flash("Username is required.", "auth-danger")  # Flash an error message for missing username
            return render_template('register.html')  # Reload the registration form
        if not password:
            flash("Password is required.", "auth-danger")  # Flash an error message for missing password
            return render_template('register.html')  # Reload the registration form

        # Check if the user already exists
        existing_user = User.get(username)  # Check if a user with the same username already exists
        if existing_user:
            flash("Username already exists.", "auth-warning")  # Flash a warning message
            return render_template('register.html')  # Reload the registration form

        # Create a new user
        try:
            User.create_user(username, password)  # Create a new user in the database
            flash("Registration successful. Please log in.", "auth-success")  # Flash a success message
            return redirect(url_for('auth.login'))  # Redirect to the login page
        except Exception as e:  # Catch any errors that occur during user creation
            flash(f"An error occurred during registration: {str(e)}", "auth-danger")  # Flash an error message
            return render_template('register.html')  # Reload the registration form with the error

    return render_template('register.html')  # Render the registration form for GET requests
