import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';

import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Observable, Subscribable, Subscription, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { ErrorDialogComponent } from '../dialog/error-dialog/error-dialog.component';
import { Message } from '../model/message.model';
import { User } from '../model/user.model';
import { StateService } from './state.service';

@Injectable({
  providedIn: 'root'
})
export class ConfigService {
  readonly urlLogin = 'http://localhost:8080/';
  readonly urlUserPec = 'http://localhost:9080/';

  constructor(private http: HttpClient, private dialog: MatDialog, private router: Router, private stateService: StateService) {

  }
  login(user: User) {
    const loginUrl = this.urlLogin + 'auth/login';
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    this.http.post(loginUrl, user, { headers }).subscribe({
      next: data => {
        let response = {
          token: ""
        };
        Object.assign(response, { token: data });
        this.stateService.token = response.token;
        this.goToComponentPec(user.email);
      },
      error: responseError => {
        this.dialog.open(ErrorDialogComponent, {
          data: {
            errorMessage: responseError.error.message
          }
        });
      }
    })
  }
  private goToComponentPec(email: string) {
    this.router.navigate(['/home']);
  }
  public getPecs(request: any): Observable<User> {
    const pecUrl = this.urlUserPec + 'api/pec/get-user-by-email';
    const headers = new HttpHeaders().set('Authorization', 'Bearer ' + this.stateService.token.access_token);
    headers.set('Access-Control-Allow-Origin', '*');
    headers.set('Content-Type', 'application/json');

    return this.http.post<User>(pecUrl, {
      "email":
        request
    }, { headers });
  }
  public getMessagesByFilter(request: any): Observable<Array<Message>> {
    const pecUrl = this.urlUserPec + 'api/pec/get-messages-by-filter';
    const headers = new HttpHeaders().set('Authorization', 'Bearer ' + this.stateService.token.access_token);
    headers.set('Access-Control-Allow-Origin', '*');
    headers.set('Content-Type', 'application/json');

    return this.http.post<Array<Message>>(pecUrl, request
      , { headers });
  }
}
