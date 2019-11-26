import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {PatientService} from '../../services/patients/patient.service';
import {EventsService} from '../../services/events/events.service';

declare var $: any;

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute, private patientService: PatientService, private eventsService: EventsService) {
    this.activatedRoute.params.subscribe(params => {
      this.params = params;
    });
  }

  private params: any = {};
  public patientInfo: Array<any>;
  public patientEvents: Array<any>;
  public openDialog = () => {
  };
  public openDeleteDialog = () => {
  };

  public getTableData = (dataTablesParameters: any, callback) => {
    const {start, length, order, columns} = dataTablesParameters;
    const {patientId} = this.params;
    const orderDir = order[0].dir;
    const orderBy = columns[order[0].column].name;

    this.eventsService.getEventByPatientId(patientId).subscribe(resp => {
        callback({
          recordsTotal: resp.lenght,
          recordsFiltered: resp.lenght,
          data: resp
        });
      }, error => {
        callback({
          recordsTotal: 0,
          recordsFiltered: 0,
          data: []
        });
      }
    );
  };

  ngOnInit() {
    const {patientId} = this.params;
    $('.menu .item').tab();
    this.patientService.getById(patientId).subscribe(value => this.patientInfo = value);
  }

}
