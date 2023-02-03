export class Message {
    sender: string;
    subject: string;
    content: string;
    priority: string;
    attachment: string;

    constructor(sender: string, subject: string, content: string, priority: string,
        attachment: string) {
        this.sender = sender;
        this.subject = subject;
        this.content = content;
        this.priority = priority;
        this.attachment = attachment;
    }
}