import requests
import random
import string


def get_random_string(length):
    letters = string.ascii_lowercase
    return ''.join(random.choice(letters) for i in range(length))

def servlet_test(string, size, access):
    url = "http://localhost:8080/Compulsory/bonus?parameter=" + string + "&size=" + size + "&access=" + access
    response = requests.get(url)

if "__main__" == __name__:
    for i in range(1, 100):
        if i % 5 == 0:
            servlet_test(get_random_string(random.randint(5, 10)), "0", "yes")
        else:
            stringValue = get_random_string(random.randint(5, 10))
            servlet_test(stringValue, str(random.randint(1, len(stringValue))), "no")