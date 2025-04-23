import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class Module1Service {
  private baseUrl = 'http://localhost:8080/api/module1'; // your Spring Boot endpoint

  constructor(private http: HttpClient) {}

  submitForm(data: any): Observable<any> {
    return this.http.post(this.baseUrl, data);
  }
}
