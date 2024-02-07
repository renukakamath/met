from flask import *
from database import *
import demjson


api=Blueprint('api',__name__)


@api.route('/login')
def login():
	data={}
	uname=request.args['username']
	pwd=request.args['password']
	q="select * from login where username='%s' and password='%s'"%(uname,pwd)
	print(q)
	w=select(q)
	if w:
		data['status']="success"
		data['data']=w
	else:
		data['status']="failed"
	return demjson.encode(data)

@api.route('/userregister')	
def userregister():
	data={}
	fn=request.args['fname']
	ln=request.args['lname']
	e=request.args['email']
	ad=request.args['address']
	ph=request.args['phone'] 
	pl=request.args['place']
	uname=request.args['username']
	pwd=request.args['password']
	q="select * from login where username='%s'"%(uname)
	res=select(q)
	if res:
		data['status']="duplicate"
	else:

		q="insert into login values (null,'%s','%s','user')"%(uname,pwd)
		id=insert(q)
		q="insert into users values (null,'%s','%s','%s','%s','%s','%s','%s')"%(id,fn,ln,e,ad,ph,pl)
		insert(q)
		data['status']="success"
	return demjson.encode(data)



@api.route('/useraddfeedback')
def useraddfeedback():
	data={}
	lid=request.args['lid']
	feedback=request.args['feedback']
	q="insert into feedback values(null,(select user_id from users where login_id='%s'),'%s',now())"%(lid,feedback)
	insert(q)
	data['status']="success"
	data['method']="useraddfeedback"
	return demjson.encode(data)


@api.route('/userviewfeedback')
def userviewfeedback():
	data={}
	lid=request.args['lid']
	q="select * from feedback where user_id=(select user_id from users where login_id='%s')"%(lid)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	data['method']="userviewfeedback"
	return demjson.encode(data)


@api.route('/useraddocc')
def useraddocc():
	data={}
	lid=request.args['lid']
	feedback=request.args['feedback']
	q="insert into occurrence values(null,(select user_id from users where login_id='%s'),'%s','pending',now())"%(lid,feedback)
	insert(q)
	data['status']="success"
	data['method']="useraddocc"
	return demjson.encode(data)


@api.route('/userviewocc')
def userviewocc():
	data={}
	lid=request.args['lid']
	q="select * from occurrence where user_id=(select user_id from users where login_id='%s')"%(lid)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	data['method']="userviewocc"
	return demjson.encode(data)


@api.route('/usersearch')
def usersearch():
	data={}
	search="%"+request.args['search']+"%"
	q="select * from trains  where train_name like '%s'" %(search)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	data['method']="userviewtrain"
	return demjson.encode(data)


@api.route('/userviewtrain')
def userviewtrain():
	data={}
	q="select * from trains"
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	data['method']="userviewtrain"
	return demjson.encode(data)


@api.route('/userviewtrips')
def userviewtrips():
	data={}
	tid=request.args['tid']
	q="select * from trip where train_id='%s'"%(tid)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	data['method']="userviewtrips"
	return demjson.encode(data)


@api.route('/userviewrate')
def userviewrate():
	data={}
	q="select * from rate"
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	data['method']="userviewrate"
	return demjson.encode(data)


@api.route('/userbooking')
def userbooking():
	data={}
	rid=request.args['rid']
	seat=request.args['seat']
	date=request.args['date']
	lid=request.args['lid']
	tid=request.args['tid']
	amount=request.args['amount']
	q="insert into booking values(null,(select user_id from users where login_id='%s'),'%s','%s','%s','%s','%s','pending')"%(lid,tid,rid,seat,amount,date)
	insert(q)
	data['status']="success"
	data['method']="userbooking"
	return demjson.encode(data)



@api.route('/userviewbooking')
def userviewbooking():
	data={}
	lid=request.args['lid']
	q="select * from booking inner join trip using(trip_id) inner join trains using(train_id) where user_id=(select user_id from users where login_id='%s')"%(lid)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	data['method']="userviewbooking"
	return demjson.encode(data)


@api.route('/usermakepayment')
def usermakepayment():
	data={}
	bid=request.args['bid']
	amount=request.args['amount']
	q="insert into payment values(null,'%s','%s',now())"%(bid,amount)
	insert(q)
	q="update booking set status='paid' where booking_id='%s'"%(bid)
	update(q)
	data['status']="success"
	return demjson.encode(data)


@api.route('/userviewstaff')
def userviewstaff():
	data={}
	q="select *,`staffs`.`place` as splace from staffs inner join stations using(station_id)"
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	return demjson.encode(data)

@api.route('/userviewmaster')
def userviewmaster():
	data={}
	q="select *,`station_masters`.`place` as splace from station_masters inner join stations using(station_id)"
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	return demjson.encode(data)



@api.route('/usersendmessage')
def usersendmessage():
	data={}
	types=request.args['type']
	log=request.args['log']
	logid=request.args['logid']
	message=request.args['message']
	q="insert into message values(null,'%s','user','%s','%s','%s','pending',now())"%(log,logid,types,message)
	insert(q)
	data['status']="success"
	data['method']="usersendmessage"
	return demjson.encode(data)


@api.route('/userviewmessage')
def userviewmessage():
	data={}
	types=request.args['type']
	log=request.args['log']
	logid=request.args['logid']
	q="select * from message where sender_id='%s' and receiver_id='%s' and receiver_type='%s'"%(log,logid,types)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	data['method']="userviewmessage"
	return demjson.encode(data)