import {Component, OnInit} from '@angular/core';
import {EventsService} from '../../shared/events/events.service';
import * as moment from 'moment';
import {TIME_FORMAT} from '../../constants';

@Component({
  selector: 'app-event-list',
  templateUrl: './event-list.component.html',
  styleUrls: ['./event-list.component.css']
})
export class EventListComponent implements OnInit {
  public persons: Array<any>;
  dtOptions: DataTables.Settings = {};

  constructor(private eventService: EventsService) {
  }

  ngOnInit(): void {
    this.dtOptions = {
      searching: false,
      // serverSide: true,
      processing: true,
      paging: false,
      ajax: (dataTablesParameters: any, callback) => {
        this.eventService.getAll().subscribe(resp => {
          this.persons = resp;
          console.log(resp);
          callback({
            recordsTotal: 2,
            // recordsFiltered: resp.recordsFiltered,
            data: resp
          });
        });
      },
      columns: [
        {
          title: 'From',
          render: (data, type, row) => {
            return moment(row.start_date).format(TIME_FORMAT);
          },
        },
        {
          title: 'To',
          render: (data, type, row) => {
            return moment(row.end_date).format(TIME_FORMAT);
          },
        },
        {
          render: (data, type, row) => {
            return row.patient.firstName + ' ' + row.patient.secondName + ' ' + row.patient.lastName;
          },
          title: 'Patient'
        },
        {
          title: 'Procedure',
          data: 'procedure.description'
        },
        {
          title: 'Room',
          data: 'room.description'
        },
        {
          render: (data, type, row) => {
            return row.staff.firstName + ' ' + row.staff.secondName + ' ' + row.staff.lastName;
          },
          title: 'Doctor'
        },
        {
          data: 'status',
          title: 'Status'
        },
      ]
    }
    ;
  }
}
