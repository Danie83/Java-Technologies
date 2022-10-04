import requests

def servlet_test(string, size, access):
    url = "http://localhost:8080/Compulsory/bonus?parameter=" + string + "&size=" + size + "&access=" + access
    response = requests.get(url)

if "__main__" == __name__:
    for i in range(1, 10):
        if i % 2 == 0:
            servlet_test("aavj", "0", "yes")
        else:
            servlet_test("aavj", "4", "no")