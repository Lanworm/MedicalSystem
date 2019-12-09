import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {PatientService} from '../../services/patients/patient.service';
import {PrescriptionService} from '../../services/prescription/prescription.service';
import * as moment from 'moment';

declare var $: any;

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  constructor(
    private activatedRoute: ActivatedRoute,
    private patientService: PatientService,
    private prescriptionService: PrescriptionService) {
    this.activatedRoute.params.subscribe(params => {
      this.params = params;
      this.patientId = params.patientId;
    });
  }

  private params: any = {};
  public patientInfo: Array<any>;
  public showDialog = false;
  public dialogData;
  public dialogError;
  public patientId;
  public startDate = moment();
  public endDate = moment().add(3, 'day');
  public buttonClass = (days) => {
    const dayDiff = this.endDate.diff(this.startDate, 'days');
    return dayDiff === days ? 'ui button active' : 'ui button';
  };


  public changeFreq = (direction) => {
    const dayDiff = this.endDate.diff(this.startDate, 'days');
    if (direction === 'prev') {
      this.endDate = moment(this.endDate).subtract(dayDiff, 'day');
      this.startDate = moment(this.startDate).subtract(dayDiff, 'day');
    } else if (direction === 'next') {
      this.endDate = moment(this.endDate).add(dayDiff, 'day');
      this.startDate = moment(this.startDate).add(dayDiff, 'day');
    }
  };

  public changeInteval = (days) => {
    this.endDate = moment(this.startDate).add(days, 'day');
  };

  public openDialog = (data) => {
    this.showDialog = true;
    this.dialogData = data;
  };

  public closeDialog = () => {
    this.showDialog = false;
    this.dialogError = null;
  };

  public getTableData = (dataTablesParameters: any, callback) => {
    const {patientId} = this.params;
    this.prescriptionService.getById(patientId).subscribe(resp => {
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

  submitForm = (params) => {
    const {patientId} = this.params;
    params.patient_id = patientId;
    if (this.dialogData) {
      params.id = this.dialogData.id;
      this.prescriptionService.update(params).subscribe((res) => {
          if (res.msg) {
            this.dialogError = res.msg;
          } else {
            this.dialogError = null;
            this.closeDialog();
            const table = $('#PrescriptionsTableList').DataTable();
            table.ajax.reload();
          }
        }, error => {
          console.error(error);
          this.closeDialog();
        }
      );
    } else {
      this.prescriptionService.add(params).subscribe(() => {
          this.closeDialog();
          const table = $('#PrescriptionsTableList').DataTable();
          table.ajax.reload();
        }, error => {
          console.error(error);
          this.closeDialog();
        }
      );
    }
  };

  ngOnInit() {
    const {patientId} = this.params;
    $('.menu .item').tab();
    this.patientService.getById(patientId).subscribe(value => this.patientInfo = value);
  }

}
