export class CustomerTS {
    customerId?: string;
    name: string;
    email: string;
    username: string;
    password: string;
    role?: string;
    constructor(name: string, email: string, username: string, password: string, role: string, customerId?: string) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.customerId = customerId;
    }

    displayInfo() {
        console.log(`Customer ID: ${this.customerId}`)
        console.log(`Name: ${this.name}`)
        console.log(`Email: ${this.email}`)
        console.log(`Password: ${this.password}`)
        console.log(`Username: ${this.username}`)
        console.log(`Role: ${this.role}`)
    }

}