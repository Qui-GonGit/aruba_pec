import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { CustomMaterialModule } from './material.module';
import { PecdashboardComponent } from './pecdashboard/pecdashboard.component';
import { AppRoutingModule } from './routing/app.routing.module';
import {FormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ConfigService } from './service/config.service';
import { ErrorDialogComponent } from './dialog/error-dialog/error-dialog.component';
import { StateService } from './service/state.service';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    PecdashboardComponent,
    ErrorDialogComponent,
    
  ],
  imports: [
    BrowserModule,
    CustomMaterialModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule
  ],
  providers: [ConfigService,StateService],
  bootstrap: [AppComponent]
})
export class AppModule { }
