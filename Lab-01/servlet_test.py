import requests
import random
import string
import threading


def get_random_string(length):
    letters = string.ascii_lowercase
    return ''.join(random.choice(letters) for i in range(length))

def servlet_test(string, size, access):
    url = "http://localhost:8080/Compulsory/bonus?parameter=" + string + "&size=" + size + "&access=" + access
    response = requests.get(url)

if "__main__" == __name__:
    for i in range(1, 100):
        if i % 5 == 0:
            thread = threading.Thread(target=servlet_test, args=(get_random_string(random.randint(2, 6)), "0", "yes",))
            thread.start()
        else:
            stringValue = get_random_string(random.randint(2, 6))
            thread = threading.Thread(target=servlet_test, args=(stringValue, str(random.randint(1, len(stringValue))), "no",))
            thread.start()
    thread.join()