import requests
from util.logger import get_logger

# Get a logger for this module
logger = get_logger(__name__)

BASE_MOCK_URI = "https://68d0db1de6c0cbeb39a2a613.mockapi.io"
USERS_PATH = "/users"

def test_create_user_should_return_201_and_have_id():
    """Tests that a user can be created via a POST request and the response is valid."""
    logger.info("Starting test: test_create_user_should_return_201_and_have_id")
    # Define the API endpoint
    url = f"{BASE_MOCK_URI}{USERS_PATH}"

    # Define the JSON payload for the new user
    payload = {
        "name": "morpheus",
        "job": "leader"
    }
    logger.debug(f"Request payload: {payload}")

    # Send the POST request
    response = requests.post(url, json=payload)

    # 1. Assert the HTTP status code is 201 (Created)
    assert response.status_code == 201, f"Expected status code 201, but got {response.status_code}"

    # Convert the response body to JSON
    response_data = response.json()

    # 2. Assert that the response body contains the correct data
    assert response_data["name"] == payload["name"]
    assert response_data["job"] == payload["job"]

    # 3. Assert that the response contains an 'id' key, and it's not empty
    assert "id" in response_data
    assert response_data["id"] is not None

    # Log the created user ID
    created_user_id = response_data["id"]
    logger.info(f"Successfully created user with ID: {created_user_id}")
