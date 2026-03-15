import { Component } from '@angular/core';
import { ComputersComponent } from './components/computers/computers.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [ComputersComponent],
  template: '<app-computers />'
})
export class AppComponent {}
