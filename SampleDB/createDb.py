import requests
import json

uri = "http://localhost:8010"

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
categories = [
    "Fantasy",
    "History",
    "Drama"
]

for category in categories:
    data = {
        "name" : category
    }
    r = requests.post(uri + "/library/category", json = data)
    print(r.request.method, r.status_code, r.reason)
    assert r.status_code == 201

# INSERT PUBLISHER
publishers = [
    "superNowa",
    "HistoricPub",
    "PWN"
]

for publisher in publishers:
    data = {
        "name" : publisher
    }
    r = requests.post(uri + "/library/publisher", json = data)
    print(r.request.method, r.status_code, r.reason)
    assert r.status_code == 201


#INSERT BOOK_IMAGE
book_images = [
    ('wiedzmin.jpg', open('wiedzmin.jpg', 'rb'), 'image/jpg'),
    ('krzyzacy.jpg', open('krzyzacy.jpg', 'rb'), 'image/jpg'),
    ('dziady.jpg', open('dziady.jpg', 'rb'), 'image/jpg')
]

for book_image in book_images:
    data = {
        "file" : book_image
    }
    r = requests.post(uri + "/library/book-image", files = data)
    print(r.request.method, r.status_code, r.reason)
    assert r.status_code == 201

#INSERT BOOK
books = [
    [
        'Wiedzmin', 
        'Pierwsze opowiadanie o wiedzminie Geralcie autorstwa Andrzeja Sapkowskiego', 
        '1',
        '1',
        '1'
    ],
    [
        'Krzyzacy',
        'Powiesc historyczna Henryka Sienkiewicza',
        '2',
        '2',
        '2'
    ],
    [
        'Dziady',
        'Cykl dramatow romantycznych Adama Mickiewicza publikowany w latach 1823-1860',
        '3',
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
        },
        "bookImage" : {
            "id" : book[4]
        }
    }
    r = requests.post(uri + "/library/book", json = data)
    print(r.request.method, r.status_code, r.reason)
    assert r.status_code == 201
