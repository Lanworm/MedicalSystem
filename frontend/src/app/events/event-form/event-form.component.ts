import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {PatientService} from '../../services/patients/patient.service';
import {EventsService} from '../../services/events/events.service';
import {ProcedureService} from '../../services/procedures/procedure.service';
import {RoomService} from '../../services/rooms/room.service';
import {StaffService} from '../../services/staff/staff.service';
import * as moment from 'moment';

declare var $: any;

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
  @Input() dialogError;
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

  eventForm = new FormGroup({});
  fromDate;
  toDate;

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
      end_date: +moment(this.toDate),
      start_date: +moment(this.fromDate),
      patient_id: this.eventForm.get('patientControl').value,
      procedure_id: this.eventForm.get('procedureControl').value,
      room_id: this.eventForm.get('roomControl').value,
      staff_id: this.eventForm.get('staffControl').value,
      status: this.eventForm.get('statusControl').value,
    };
    console.log(this.eventForm);
    this.submitForm(params);
  };

  ngOnInit() {

    const nowDate = new Date;
    const {
      patient: {id: patientId = null} = {},
      procedure: {id: procedureId = null} = {},
      room: {id: roomId = null} = {},
      staff: {id: staffId = null} = {},
      status = null,
      start_date = nowDate,
      end_date = nowDate,
    } = this.dialogData || {};

    console.log(moment(start_date).toDate());

    (<any> $('#startDate')).calendar({
      endCalendar: $('#endDate'),
      onChange: (date, text) => {
        this.fromDate = date;
      },
    });
    (<any> $('#endDate')).calendar({
      startCalendar: $('#startDate'),
      onChange: (date, text) => {
        this.toDate = date;
      },
    });

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

    this.eventForm.addControl('patientControl', new FormControl(patientId, Validators.required));
    this.eventForm.addControl('procedureControl', new FormControl(procedureId, Validators.required));
    this.eventForm.addControl('roomControl', new FormControl(roomId, Validators.required));
    this.eventForm.addControl('staffControl', new FormControl(staffId, Validators.required));
    this.eventForm.addControl('statusControl', new FormControl(status, Validators.required));
    this.eventForm.addControl('fromControl', new FormControl(moment(start_date).toDate(), Validators.required));
    this.eventForm.addControl('toControl', new FormControl(moment(end_date).toDate(), Validators.required));

  }

}
