import {Component, OnInit} from '@angular/core';
import {AuthService} from '../services/auth/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  constructor(private authService: AuthService, private router: Router) {
  }

  public onLogout = () => {
    this.authService.logout().subscribe(response => {
      console.log(response);
      this.router.navigate(['/login']);
    });
  };

  ngOnInit() {
  }

}
