import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-heroes-list-better',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './heroes-list-better.component.html',
  styleUrl: './heroes-list-better.component.css'
})
export class HeroesListBetterComponent {
  @Input() heroes: {name: string, id: string}[] = [];
  ngOnInit() {
    console.log('heroes:', this.heroes)
  }
}