import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../model/user.model';
import { ConfigService } from '../service/config.service';
import { StateService } from '../service/state.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  showSpinner: any;
  email: string = "";
  password: string = "";
  constructor(private router: Router, private configService: ConfigService,private stateService: StateService) {

  }
  user = new User();
  ngOnInit() {

  }

  login(): void {

    this.user.username = this.email;
    this.user.email = this.email;
    this.user.password = this.password;
    this.stateService.email = this.email;
    this.configService.login(this.user);
  }

}
