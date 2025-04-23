
import { Component } from '@angular/core';
import { Module1FormComponent } from './module1-form/module1-form.component';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [Module1FormComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'my-angular-frontend';
}
