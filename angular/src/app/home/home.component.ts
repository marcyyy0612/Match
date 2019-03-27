import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

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
  }

  public getUsers(): void {
    const url = 'http://localhost:9000/users/1';
    this.http.get(url).subscribe(res => {
      console.log(res);
    });
  }
}
