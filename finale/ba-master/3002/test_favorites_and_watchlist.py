import unittest
from app import app, db
from models import User, UserMovies
from sqlalchemy import event
from sqlalchemy.engine import Engine
import sys
import os
sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))

# Enable foreign key constraints for SQLite database
@event.listens_for(Engine, "connect")
def set_sqlite_pragma(dbapi_connection, connection_record):
    """Ensure SQLite enforces foreign key constraints."""
    cursor = dbapi_connection.cursor()
    cursor.execute("PRAGMA foreign_keys=ON")  # Turn on foreign key support
    cursor.close()

class WatchlistFavoritesTestCase(unittest.TestCase):
    """Unit test class to test the functionality of the watchlist and favorites features."""

    def setUp(self):
        """Set up the test environment before each test."""
        self.app = app  # Reference to the Flask app instance
        self.app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///:memory:'  # Use in-memory SQLite database
        self.app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False  # Disable SQLAlchemy event notifications
        self.app.testing = True  # Enable Flask's testing mode
        self.client = self.app.test_client()  # Test client to simulate HTTP requests

        with self.app.app_context():
            db.drop_all()  # Drop all existing tables
            db.create_all()  # Create fresh database tables
            # Add a test user to the database
            user = User(username='testuser', password='testpassword')
            db.session.add(user)
            db.session.commit()

    def tearDown(self):
        """Clean up the test environment after each test."""
        with self.app.app_context():
            db.session.remove()  # Remove the session
            db.drop_all()  # Drop all tables

    def login_user(self):
        """Helper method to log in the test user."""
        response = self.client.post('/auth/login', data={
            'username': 'testuser',  # Test user's username
            'password': 'testpassword'  # Test user's password
        }, follow_redirects=True)  # Follow redirects after login
        return response

    def test_add_movie_to_watchlist(self):
        """Test if a movie can be added to the user's watchlist."""
        self.login_user()  # Log in the test user
        movie_id = 123  # Movie ID to add
        response = self.client.post(f'/watchlist/add/{movie_id}', follow_redirects=True)
        self.assertEqual(response.status_code, 200)  # Check if request was successful

        with self.app.app_context():
            # Verify the movie is added to the watchlist
            user_movie = UserMovies.query.filter_by(
                user_id=1,
                movie_id=movie_id,
                category='watchlist'
            ).first()
            self.assertIsNotNone(user_movie)  # Ensure the movie exists in the watchlist

    def test_remove_movie_from_watchlist(self):
        """Test if a movie can be removed from the user's watchlist."""
        self.login_user()  # Log in the test user
        movie_id = 123  # Movie ID to add and then remove

        # Add the movie to the watchlist first
        self.client.post(f'/watchlist/add/{movie_id}', follow_redirects=True)

        # Remove the movie from the watchlist
        response = self.client.post(f'/watchlist/remove/{movie_id}', follow_redirects=True)
        self.assertEqual(response.status_code, 200)  # Check if request was successful

        with self.app.app_context():
            # Verify the movie is no longer in the watchlist
            user_movie = UserMovies.query.filter_by(
                user_id=1,
                movie_id=movie_id,
                category='watchlist'
            ).first()
            self.assertIsNone(user_movie)  # Ensure the movie is removed

    def test_view_watchlist(self):
        """Test if the watchlist can be viewed."""
        self.login_user()  # Log in the test user
        movie_id = 123  # Movie ID to add

        # Add the movie to the watchlist
        self.client.post(f'/watchlist/add/{movie_id}', follow_redirects=True)

        # Send GET request to view the watchlist
        response = self.client.get('/watchlist', follow_redirects=True)
        self.assertEqual(response.status_code, 200)  # Check if request was successful
        self.assertIn(b"Your Watchlist", response.data)  # Check if the page contains watchlist content

    def test_add_movie_to_favorites(self):
        """Test if a movie can be added to the user's favorites."""
        self.login_user()  # Log in the test user
        movie_id = 456  # Movie ID to add
        response = self.client.post(f'/favorites/add/{movie_id}', follow_redirects=True)
        self.assertEqual(response.status_code, 200)  # Check if request was successful

        with self.app.app_context():
            # Verify the movie is added to favorites
            user_movie = UserMovies.query.filter_by(
                user_id=1,
                movie_id=movie_id,
                category='favorites'
            ).first()
            self.assertIsNotNone(user_movie)  # Ensure the movie exists in the favorites

    def test_remove_movie_from_favorites(self):
        """Test if a movie can be removed from the user's favorites."""
        self.login_user()  # Log in the test user
        movie_id = 456  # Movie ID to add and then remove

        # Add the movie to favorites first
        self.client.post(f'/favorites/add/{movie_id}', follow_redirects=True)

        # Remove the movie from favorites
        response = self.client.post(f'/favorites/remove/{movie_id}', follow_redirects=True)
        self.assertEqual(response.status_code, 200)  # Check if request was successful

        with self.app.app_context():
            # Verify the movie is no longer in favorites
            user_movie = UserMovies.query.filter_by(
                user_id=1,
                movie_id=movie_id,
                category='favorites'
            ).first()
            self.assertIsNone(user_movie)  # Ensure the movie is removed

    def test_view_favorites(self):
        """Test if the favorites list can be viewed."""
        self.login_user()  # Log in the test user
        movie_id = 456  # Movie ID to add

        # Add the movie to favorites
        self.client.post(f'/favorites/add/{movie_id}', follow_redirects=True)

        # Send GET request to view the favorites list
        response = self.client.get('/favorites', follow_redirects=True)
        self.assertEqual(response.status_code, 200)  # Check if request was successful
        self.assertIn(b"Your Favorites", response.data)  # Check if the page contains favorites content


if __name__ == '__main__':
    unittest.main()
