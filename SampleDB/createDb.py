import requests
import json

uri = "http://localhost:8082"

# INSERT AUTHORS
authors = {
    'Andrzej': 'Sapkowski', 
    'Henryk': 'Sienkiewicz', 
    'Adam' : 'Mickiewicz'
}

for key in authors:
    data = {
	    "firstName" : key,
	    "lastName" : authors[key]
    }
    r = requests.post(uri + "/library/author", json = data)
    print(r.request.method, r.status_code, r.reason)
    assert r.status_code == 201

# INSERT CATEGORY 
categories = {
    "Drama",
    "History",
    "Fantasy"
}

for category in categories:
    data = {
        "name" : category
    }
    r = requests.post(uri + "/library/category", json = data)
    print(r.request.method, r.status_code, r.reason)
    assert r.status_code == 201

# INSERT PUBLISHER
publishers = {
    "superNowa",
    "HistoricPub",
    "PWN"
}

for publisher in publishers:
    data = {
        "name" : publisher
    }
    r = requests.post(uri + "/library/publisher", json = data)
    print(r.request.method, r.status_code, r.reason)
    assert r.status_code == 201

#INSERT BOOK
books = [
    [
        'Wiedzmin', 
        'Pierwsze opowiadanie o wiedzminie Geralcie autorstwa Andrzeja Sapkowskiego', 
        '1',
        '1'    
    ],
    [
        'Krzyzacy',
        'Powiesc historyczna Henryka Sienkiewicza',
        '2',
        '2'
    ],
    [
        'Dziady',
        'Cykl dramatow romantycznych Adama Mickiewicza publikowany w latach 1823-1860',
        '3',
        '3'
    ]
]

for book in books:
    data = {
        "title" : book[0],
        "description" : book[1],
        "author" : {
            "id" : book[2]
        },
        "category" : {
            "id" : book[3]
        }
    }
    r = requests.post(uri + "/library/book", json = data)
    print(r.request.method, r.status_code, r.reason)
    assert r.status_code == 201
