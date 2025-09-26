import logging
import sys

def get_logger(name):
    """Creates and configures a logger instance."""
    # Create a logger
    logger = logging.getLogger(name)
    logger.setLevel(logging.DEBUG) # Set the lowest level to capture all messages

    # Create a handler to print to the console (stdout)
    # If a handler for this logger already exists, don't add another one
    if not logger.handlers:
        handler = logging.StreamHandler(sys.stdout)

        # Create a formatter and set it for the handler
        formatter = logging.Formatter(
            '%(asctime)s - %(name)s - %(levelname)s - %(message)s'
        )
        handler.setFormatter(formatter)

        # Add the handler to the logger
        logger.addHandler(handler)

    return logger
