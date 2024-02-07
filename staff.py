from flask import *
from database import *
staff=Blueprint('staff',__name__)
@staff. route('/staffhome')
def staffhome():
	return render_template('staffhome.html')

@staff. route('/status',methods=['post','get'])
def status():
	data={}
	q="select * from staffs where login_id='%s'"%(session['lid'])
	print(q)
	res=select(q)

	station=res[0]['station_id']
	r=select(q)
	print(r)
	data['st']=r
	q="select * from trains"
	r=select(q)
	print(r)
	data['train']=r
	#tid=request.args['tid']
	#data['tid']=tid
	# q="select * from trains"
	# r=select(q)
	# print(r)
	# data['st']=r
	q="SELECT * FROM STATUS INNER JOIN `trains`USING (`train_id`) INNER JOIN `stations` ON(`stations`.`station_id`=status.`current_station_id`) where station_id='%s'"%(station)
	print(q)
	r=select(q)
	print(r)
	data['status']=r
	if "submit" in request.form:
		s=request.form['status']
		tid=request.form['train']
		
		q="insert into status values (null,'%s','%s','%s')"%(tid,station,s)
		print(q)
		insert(q)
		return redirect(url_for('staff.status',tid=tid))
	return render_template('status.html',data=data)
@staff.route('/users',methods=['post','get'])
def users():
	data={}
	q="select * from users"
	r=select(q)
	data['users']=r
	return render_template('users.html',data=data)
@staff. route('/staffmessage',methods=['post','get'])
def staffmessage():
	data={}
	lid=session['lid']
	q="select * from message inner join users on(message.sender_id=users.login_id) where sender_type='user' and receiver_id='%s'"%(lid)
	r=select(q)
	data['message']=r
	if "submit" in request.form:
		m=request.form['msg']
		mid=request.form['mid']
		
		q="update  message set reply='%s' where message_id='%s'"%(m,mid)
		res=update(q)
		return redirect(url_for('staff.staffmessage'))

	return render_template('staffmessage.html',data=data)
