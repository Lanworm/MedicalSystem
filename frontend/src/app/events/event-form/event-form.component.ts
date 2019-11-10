import {Component, Input, OnInit} from '@angular/core';
import {FormControl} from '@angular/forms';
import {PatientService} from '../../shared/patients/patient.service';
import {EventsService} from '../../shared/events/events.service';
import {ProcedureService} from '../../shared/procedures/procedure.service';
import {RoomService} from '../../shared/rooms/room.service';
import {StaffService} from '../../shared/staff/staff.service';
import * as moment from 'moment';


@Component({
  selector: 'app-event-form',
  templateUrl: './event-form.component.html',
  styleUrls: ['./event-form.component.css']
})
export class EventFormComponent implements OnInit {
  @Input() dialogData;
  @Input() closeDialog;
  @Input() showDialog: boolean;
  @Input() submitForm;
  public patientsList: Array<any> = [];
  public procedureList: Array<any> = [];
  public roomList: Array<any> = [];
  public staffList: Array<any> = [];
  public eventStatus = [
    {key: 'IN_WORK', value: 'In work'},
    {key: 'DONE', value: 'Done'},
    {key: 'CANCELED', value: 'Canceled'},
    {key: 'ASSIGNED', value: 'Assigned'},
  ];

  patientControl: FormControl;
  procedureControl: FormControl;
  roomControl: FormControl;
  staffControl: FormControl;
  statusControl: FormControl;
  fromControl: FormControl;
  toControl: FormControl;

  constructor(
    private patientService: PatientService,
    private eventsService: EventsService,
    private procedureService: ProcedureService,
    private roomService: RoomService,
    private staffService: StaffService,
  ) {
  }

  onSubmit = () => {
    const params = {
      end_date: +moment(this.toControl.value),
      start_date: +moment(this.fromControl.value),
      patient_id: this.patientControl.value,
      procedure_id: this.procedureControl.value,
      room_id: this.roomControl.value,
      staff_id: this.staffControl.value,
      status: this.statusControl.value,
    };
    this.submitForm(params);
  };

  ngOnInit() {
    (<any> $('#startDate')).calendar({endCalendar: $('#endDate')});
    (<any> $('#endDate')).calendar({startCalendar: $('#startDate')});
    const nowDate = new Date;
    const {
      patient: {id: patientId = null} = {},
      procedure: {id: procedureId = null} = {},
      room: {id: roomId = null} = {},
      staff: {id: staffId = null} = {},
      status = null,
      start_date = nowDate,
      end_date = nowDate
    } = this.dialogData || {};
    Promise.all([
      this.patientService.getAll().toPromise(),
      this.procedureService.getAll().toPromise(),
      this.roomService.getAll().toPromise(),
      this.staffService.getAll().toPromise(),
    ]).then(result => {
      this.patientsList = result[0];
      this.procedureList = result[1];
      this.roomList = result[2];
      this.staffList = result[3];
    });
    this.patientControl = new FormControl(patientId);
    this.procedureControl = new FormControl(procedureId);
    this.roomControl = new FormControl(roomId);
    this.staffControl = new FormControl(staffId);
    this.statusControl = new FormControl(status);
    this.fromControl = new FormControl(moment(start_date).toDate());
    this.toControl = new FormControl(moment(end_date).toDate());
  }

}
