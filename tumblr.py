import urllib.request, json

blogName = input("Which blog? ")
print("Getting photos from " + blogName + "!")

tumblr_url = 'https://api.tumblr.com/v2/blog/'+blogName+'/posts/photo?limit=50&api_key=00YoSRZpHrFewyU5jGywuz1cEzWqjD3gLHs3OrpX1yOgG8bjR7'
with urllib.request.urlopen(tumblr_url) as url:
    data = json.loads(url.read().decode())
response = data['response']	
posts = response['posts']
for post in posts:
	photos = post['photos']
	zero = photos[0]
	original_size = zero['original_size']
	photourl = original_size['url']
	print(photourl)
input()