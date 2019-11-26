import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {PatientService} from '../../services/patients/patient.service';

declare var $: any;

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {
  private params: any = {};
  public patientInfo: Array<any>;

  constructor(private activatedRoute: ActivatedRoute, private patientService: PatientService) {
    this.activatedRoute.params.subscribe(params => {
      this.params = params;
    });
  }

  ngOnInit() {
    const {patientId} = this.params;
    $('.menu .item').tab();
    this.patientService.getById(patientId).subscribe(value => this.patientInfo = value);
  }

}
