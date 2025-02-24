import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-star-rating',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="star-rating">
      <span *ngFor="let star of stars; let i = index" (click)="rate(i + 1)">
        <i class="bi" 
           [ngClass]="{
             'bi-star-fill': i < rating,
             'bi-star-half': i < rating + 0.5 && i >= rating,
             'bi-star': i >= rating + 0.5
           }"></i>
      </span>
    </div>
  `,
  styles: [`
    .star-rating {
      font-size: 24px;
    }
    .bi {
      cursor: pointer;
    }
  `]
})
export class StarRatingComponentComponent {
  @Input() rating: number = 0;
  @Output() ratingChange: EventEmitter<number> = new EventEmitter<number>();
  stars: boolean[] = Array(5).fill(false);

  rate(rating: number): void {
    this.rating = rating;
    this.ratingChange.emit(this.rating);
  }
}
