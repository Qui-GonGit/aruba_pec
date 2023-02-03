import { PEC } from "./pec.model";

export class User {
    idUtente:string;
    username: string;
    password: string;
    firstName: string;
    lastName: string;
    email:string;
    idAruba:string;
    pecs: Array<PEC> = [];
    constructor() {
        this.email="";
        this.idAruba ="";
        this.idUtente = "";
        this.firstName = "";
        this.lastName = "";
        this.password = "";
        this.username = "";
    }

    public setPecs(pecs: Array<PEC>) {
        this.pecs = pecs;
    }
    public setFirstName(name:string){
        this.firstName = name;
    }
    public setLastname(name:string){
        this.lastName = name;
    }
}