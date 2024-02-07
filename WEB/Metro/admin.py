from flask import *
from database import *
admin=Blueprint('admin',__name__)
@admin. route('/adminhome')
def adminhome():

	return render_template('adminhome.html')
@admin. route('/stations',methods=['post','get'])
def stations():
	data={}
	q="select * from stations"
	r=select(q)
	data['stations']=r
	if "submit" in request.form:
		n=request.form['name']
		no=request.form['number']
		p=request.form['place']
		q="insert into stations values (null,'%s','%s','%s')"%(n,no,p)
		res=insert(q)
		return redirect(url_for('admin.stations'))
	
	if'action' in request.args:
		action=request.args['action']
		id=request.args['id']
	else:
		action=None
	if action=='delete':
		q="delete from stations where station_id='%s'"%(id)
		delete(q)
		return redirect(url_for('admin.stations'))
	if action=='update':
		q="select * from stations where station_id='%s'"%(id)
		r=select(q)
		data['pro']=r
	if "ref" in request.form:
		n=request.form['station_name']
		no=request.form['phone']
		p=request.form['place']
		q="update stations set station_name='%s',phone='%s',place='%s' where station_id='%s'"%(n,no,p,id)
		update(q)
		return redirect(url_for('admin.stations'))
	return render_template('stations.html',data=data)

	 

@admin. route('/station_masters',methods=['post','get'])
def station_masters():
	data={}
	q="select * from stations"
	r=select(q)
	data['station']=r
	if "submit" in request.form:
		s=request.form['station']
		f=request.form['fname']
		l=request.form['lname']
		e=request.form['email']
		a=request.form['address']
		no=request.form['number']
		p=request.form['place']
		qu=request.form['Qualification']
		n=request.form['name']
		p=request.form['password']
		q="insert into login values (null,'%s','%s','master')"%(n,p)
		res=insert(q)
		print(q)
		q="insert into station_masters values (null,'%s','%s','%s','%s','%s','%s','%s','%s','%s')"%(res,s,f,l,e,a,no,p,qu)
		print(q)
		insert(q)
		return redirect(url_for('admin.station_masters'))
		@admin.route('/station_masters',methods=['post','get'])
		def station_mastersview():
			data={}
	q="select * from station_masters"
	r=select(q)
	data['master']=r

	if'action' in request.args:
		action=request.args['action']
		id=request.args['id']
	else:
		action=None
	if action=='delete':
		q="delete from station_masters where master_id='%s'"%(id)
		delete(q)
		return redirect(url_for('admin.station_masters'))
	if action=='update':
		q="select * from station_masters where master_id='%s'"%(id)
		r=select(q)
		data['pro']=r
	if "submit" in request.form:
		f=request.form['fname']
		l=request.form['lname']
		e=request.form['email']
		a=request.form['address']
		no=request.form['number']
		p=request.form['place']
		qu=request.form['Qualification']
		
		q="update stations set first_name='%s',last_name='%s',email='%s',address='%s',number='%s',place='%s',Qualification='%s' where master_id='%s'"%(n,no,p,id)
		update(q)
		return redirect(url_for('admin.station_masters'))
	return render_template('master.html',data=data)

@admin. route('/staffs',methods=['post','get'])
def staffs():
	data={}
	q="select * from stations"
	r=select(q)
	print(r)
	data['st']=r


	q="select * from staffs"
	r=select(q)
	data['staff']=r
	if "submit" in request.form:
		s=request.form['station']
		f=request.form['fname']
		l=request.form['lname']
		e=request.form['email']
		a=request.form['address']
		no=request.form['number']
		p=request.form['place']
		qu=request.form['Qualification']
		n=request.form['name']
		p=request.form['password']
		q="insert into login values (null,'%s','%s','staffs')"%(n,p)
		res=insert(q)
		print(q)
		q="insert into staffs values (null,'%s','%s','%s','%s','%s','%s','%s','%s','%s')"%(res,s,f,l,e,a,no,p,qu)
		print(q)
		insert(q)
		return redirect(url_for('admin.staffs'))
	if'action' in request.args:
		action=request.args['action']
		id=request.args['id']
	else:
		action=None
	if action=='delete':
		q="delete from staffs where staff_id='%s'"%(id)
		delete(q)
		return redirect(url_for('admin.staffs'))
	if action=='update':
		q="select * from staffs where staff_id='%s'"%(id)
		r=select(q)
		data['pro']=r
	if "submit" in request.form:
		f=request.form['fname']
		l=request.form['lname']
		e=request.form['email']
		a=request.form['address']
		no=request.form['number']
		p=request.form['place']
		qu=request.form['Qualification']
		
		q="update staffs set first_name='%s',last_name='%s',email='%s',address='%s',number='%s',place='%s',Qualification='%s' where staffs_id='%s'"%(n,no,p,id)
		update(q)
		return redirect(url_for('admin.staffs'))

	return render_template('staff.html',data=data)
@admin. route('/trains',methods=['post','get'])
def trains():
	data={}
	q="select * from stations"
	r=select(q)
	print(r)
	data['st']=r
	q="select * from trains"
	r=select(q)
	data['train']=r
	if "submit" in request.form:
		t=request.form['name']
		c=request.form['compartments']

		q="insert into trains values (null,'%s','%s')"%(t,c)
		print(q)
		insert(q)
		return redirect(url_for('admin.trains'))
	if'action' in request.args:
		action=request.args['action']
		id=request.args['id']
	else:
		action=None
	if action=='delete':
		q="delete from trains where train_id='%s'"%(id)
		delete(q)
		return redirect(url_for('admin.trains'))
	if action=='update':
		q="select * from trains where train_id='%s'"%(id)
		r=select(q)
		data['pro']=r
	if "submit" in request.form:
		t=request.form['name']
		c=request.form['compartments']
		q="update trains set name='%s',compartments='%s'"%(t,c,id)
		update(q)
		return redirect(url_for('admin.trains'))
	return render_template('train.html',data=data)

@admin. route('/rate',methods=['post','get'])
def rate():
	data={}
	q="select * from rate"
	r=select(q)
	print(r)
	data['st']=r
	q="select * from rate"

	r=select(q)
	data['rate']=r
	if "submit" in request.form:
		m=request.form['mcharge']
		s=request.form['scharge']
		u=request.form['updated']

		q="insert into rate values (null,'%s','%s','%s')"%(m,s,u)
		print(q)
		insert(q)
		return redirect(url_for('admin.rate'))
	if'action' in request.args:
		action=request.args['action']
		id=request.args['id']
	else:
		action=None
	if action=='delete':
		q="delete from rate where rate_id='%s'"%(id)
		delete(q)
		return redirect(url_for('admin.rate'))
	if action=='update':
		q="select * from rate where rate_id='%s'"%(id)
		r=select(q)
		data['pro']=r
	if "submit" in request.form:
		m=request.form['mcharge']
		s=request.form['scharge']
		u=request.form['updated']
		q="update rate set mcharge='%s',scharge='%s',updated='%s'"%(m,s,u,id)
		update(q)
		return redirect(url_for('admin.rate'))	
	return render_template('rate.html',data=data)


@admin. route('/trip',methods=['post','get'])
def trip():
	data={}
	tid=request.args['tid']
	data['tid']=tid
	q="SELECT * FROM `trip` "
	res=select(q)
	data['trip']=res
	q="select * from `stations`"
	r=select(q)
	data['st']=r
	if "submit" in request.form:
	 	fs=request.form['fstation']
	 	ts=request.form['tstation']
	 	st=request.form['stime']
	 	e=request.form['etime']
	 	t=request.form['towards']
	 	q="insert into trip values (null,'%s','%s','%s','%s','%s','%s')"%(tid,fs,ts,st,e,t)
	 	print(q)
	 	insert(q)
	 	return redirect(url_for('admin.trip',tid=tid))
	if 'action' in request.args:
	 	action=request.args['action']
	 	id=request.args['id']
	else:
	 	action=None
	if action=='delete':
	 	q="delete from trip where trip_id='%s'"%(id)
	 	delete(q)
	 	return redirect(url_for('admin.trip',tid=tid))
	if action=='update':
	 	q="select * from trip where trip_id='%s'"%(id)
	 	r=select(q)
	 	data['pro']=r
	
	return render_template('trip.html',data=data)
@admin. route('/adminstatus',methods=['post','get'])
def adminstatus():
	data={}
	

	
	tid=request.args['tid']
	
	q="SELECT * FROM STATUS INNER JOIN `trains`USING (`train_id`) INNER JOIN `stations` ON(`stations`.`station_id`=status.`current_station_id`) where train_id='%s'"%(tid)
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
		return redirect(url_for('admin.adminstatus',tid=tid))
	return render_template('adminstatus.html',data=data)
@admin.route('/booking',methods=['post','get'])
def booking():
	data={}
	q="select * from booking inner join users USING(user_id)"
	r=select(q)
	data['booking']=r
	return render_template('bookingview.html',data=data)

@admin.route('/feedback',methods=['post','get'])
def feedback():
	data={}
	q="select * from feedback inner join users USING(user_id)"
	r=select(q)
	data['feedback']=r
	return render_template('feedback.html',data=data)

