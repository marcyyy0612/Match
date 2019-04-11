import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { stringify } from '@angular/core/src/render3/util';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(
    private http: HttpClient
  ) { }

  ngOnInit() {
    this.getUsers();
    this.signin();
  }

  public getUsers(): void {
    const url = 'http://localhost:9000/users';
    this.http.get(url).subscribe(res => {
      console.log(res);
    });
  }

  public signin(): void {
    const url = 'http://localhost:9000/signin';
    const body = {
      email: "marcy@sample.com",
      password: "password"
    }
    this.http.post(url, body).subscribe(res => {
      console.log(res);
    })
  }
}
