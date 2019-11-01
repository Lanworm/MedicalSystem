import {Component, OnInit} from '@angular/core';
import {DoctorService} from '../shared/doctor/doctor.service';

@Component({
  selector: 'app-doctors-list',
  templateUrl: './doctors-list.component.html',
  styleUrls: ['./doctors-list.component.css']
})
export class DoctorsListComponent implements OnInit {
  public doctors: Array<any>;

  constructor(private doctorService: DoctorService) {
  }

  ngOnInit() {
    this.doctorService.getAll().subscribe(data => {
      this.doctors = data;
    });
  }

}
