import { Message } from "./message.model";

export class PEC {
  name: string;
  id : string;
  messages: Array<Message>
  constructor() {
    this.name = "";
    this.messages = [];
    this.id="";
  }
}