from flask import *
from database import *
import demjson


api=Blueprint('api',__name__)


@api.route('/login')
def login():
	data={}
	uname=request.args['username']
	pwd=request.args['Password']
	q="select * from login where username='%s' and password='%s'"%(uname,pwd)
	print(q)
	w=select(q)
	if w:
		data['status']="success"
	else:
		data['status']="failed"
	return demjson.encode(data)

@api.route('/registration')	
def registration():
	data={}
	fn=request.args['fname']
	ln=request.args['lname']
	e=request.args['email']
	ad=request.args['address']
	ph=request.args['phone'] 
	pl=request.args['place']
	uname=request.args['usename']
	pwd=request.args['password']
	q="insert into login values (null,'%s','%s','user')"%(n,p)
	res=insert(q)
	q="insert into users values (null,'%s','%s','%s','%s','%s','%s','%s','%s')"%(fn,ln,e,ad,ph,pl,uname,pwd)
	print(q)
	insert(q)
	if w:
		data['status']="success"
	else:
		data['status']="failed"
	return demjson(data)

		
