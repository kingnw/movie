from setuptools import setup, find_packages

setup(
    name="movie_recommendation",  # Replace with your project name
    version="1.0",
    packages=find_packages(),
    include_package_data=True,
    install_requires=[
        "Flask",
        "Flask-SQLAlchemy",
        "Flask-Login",
        "Flask-Caching",
        "requests",
    ],
)
