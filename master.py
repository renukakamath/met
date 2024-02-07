from flask import *
from database import *
master=Blueprint('master',__name__)
@master. route('/masterhome')
def masterhome():

	return render_template('masterhome.html')
@master.route('/staffview',methods=['post','get'])
def staffview():
	data={}
	q="select * from staffs"
	r=select(q)
	data['staffs']=r
	return render_template('staffview.html',data=data)

@master.route('/trainsview',methods=['post','get'])
def trainsview():
	data={}
	q="select * from  trains"
	r=select(q)
	data['train']=r
	return render_template('trainsview.html',data=data)
	 
#@master. route('/feedback',methods=['post','get'])
#def feedback():
#	data={}
#	q="select * from feedback"
#	r=select(q)
#	data['feedback']=r
#	if "submit" in request.form:
#		dt=request.form['datetime']
#		q="insert into feedback values (null,'%s','%s')"%(f,dt)
#		res=insert(q)
#		return redirect(url_for('admin.feedback'))
#	return render_template('feedback.html',data=data)
@master. route('/message',methods=['post','get'])
def message():
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
		return redirect(url_for('master.message'))

	return render_template('message.html',data=data)