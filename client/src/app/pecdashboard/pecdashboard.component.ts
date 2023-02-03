import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Message } from '../model/message.model';
import { PEC } from '../model/pec.model';
import { User } from '../model/user.model';
import { ConfigService } from '../service/config.service';
import { StateService } from '../service/state.service';

@Component({
  selector: 'app-pecdashboard',
  templateUrl: './pecdashboard.component.html',
  styleUrls: ['./pecdashboard.component.css']
})
export class PecdashboardComponent implements OnInit {
  displayedColumns: string[] = ['sender', 'subject', 'content', 'priority','attachment'];
  dataSource = new MatTableDataSource<Message>();
  user: User = new User;
  selectedValue: string = "";
  hasAttachment:boolean=false;
  requestSender: string ="";
  requestContent:string ="";
  requestSubject:string ="";
  requestPriority:String="";
  pecname: string="";
  constructor(private configService: ConfigService, private stateService: StateService) { }

  ngOnInit() {
   this.configService.getPecs(this.stateService.email).subscribe(user => {
    this.user.setFirstName(user.firstName);
    this.user.setLastname(user.lastName);
    this.user.idAruba = user.idAruba;
    this.user.setPecs(user.pecs);   
  });
   
  }
  settingsTable(target:any){  
    this.pecname = target.value;
    this.dataSource.data = this.user.pecs.filter(pec => pec.name === target.value).flatMap(pec => pec.messages);
  }
  setChecked(event:any){
  
  }
  sendToBE(){
    let request = {
      "idUtente": this.user.idAruba,
      "pecName": this.pecname ,
      "sender": this.requestSender,
      "subject": this.requestSubject,
      "text": this.requestContent,
      "priority": this.requestPriority,
      "attachment" : this.hasAttachment
    }
    this.configService.getMessagesByFilter(request).subscribe(response => {
      this.dataSource.data = response;
    });
  }
}
