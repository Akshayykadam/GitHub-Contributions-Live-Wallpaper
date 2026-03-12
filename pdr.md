# GitHub Contributions Live Wallpaper (Android)

Comprehensive Documentation  
PRD + Technical Architecture + Design Document

---

# 1. Product Requirements Document (PRD)

## 1.1 Product Overview

The **GitHub Contributions Live Wallpaper** is an Android live wallpaper application that visualizes a user's GitHub contribution graph for the current year directly on their device home screen.

The wallpaper renders a **dynamic contribution heatmap** similar to GitHub's profile graph. Each square represents a day in the year, with color intensity indicating commit activity.

The wallpaper updates automatically and optionally animates new contributions.

The goal is to transform developer productivity into a **living visual artifact on the device wallpaper**.

---

## 1.2 Goals

### Primary Goals

- Visualize GitHub contributions in real time
- Provide a visually pleasing live wallpaper
- Lightweight battery-efficient rendering
- Customization options for developers

### Secondary Goals

- Encourage coding streaks
- Provide motivation through visual feedback
- Support themes and animations

---

## 1.3 Target Users

### Primary Users

Software developers who use GitHub regularly.

### Secondary Users

- Open source contributors
- Students learning programming
- Developers tracking daily coding streaks

---

## 1.4 Key Features

### Live Contribution Graph

Displays the GitHub contributions calendar for the current year.

### Automatic Data Sync

Fetches contribution data periodically using the GitHub GraphQL API.

### Animated Updates

When a new contribution appears, the corresponding tile animates.

### Themes

Selectable visual themes such as:

- Classic GitHub Green
- Neon Matrix
- Purple Developer Theme
- Dark Mode

### Performance Optimization

Rendering optimized for minimal CPU and battery usage.

---

## 1.5 Functional Requirements

**FR1**  
The system must fetch contribution data from GitHub.

**FR2**  
The system must display contributions in a grid representing the calendar year.

**FR3**  
The wallpaper must refresh data periodically.

**FR4**  
The user must be able to enter their GitHub username.

**FR5**  
The system must render heatmap colors based on commit count.

**FR6**  
The wallpaper must run as an Android Live Wallpaper Service.

**FR7**  
The application must support Android 8.0+.

---

## 1.6 Non-Functional Requirements

### Performance

- Render updates under a 16ms frame budget

### Battery

- Background network updates limited to periodic intervals

### Security

- GitHub token stored securely

### Reliability

- Network failures fallback to cached data

---

## 1.7 Success Metrics

- Daily active users
- Wallpaper usage duration
- Contribution update success rate

---

# 2. Technical Architecture Document

## 2.1 System Architecture

The system contains four main layers.

### Layers

- User Interface Layer
- Wallpaper Rendering Engine
- Data Layer
- Network Layer

### Data Flow


GitHub API
↓
GraphQL Response
↓
Data Parser
↓
Contribution Model
↓
Renderer
↓
Android Live Wallpaper


---

## 2.2 Technology Stack

| Component | Technology |
|--------|--------|
Language | Kotlin
Minimum SDK | Android 26
Rendering | Canvas API or OpenGL ES
Networking | Retrofit + OkHttp
JSON Parsing | Moshi / Kotlin Serialization
Storage | Room Database
Background Tasks | WorkManager
Dependency Injection | Hilt

---

## 2.3 GitHub API Integration

The application uses the **GitHub GraphQL API**.

### Example Query

```graphql
query {
  user(login: "USERNAME") {
    contributionsCollection {
      contributionCalendar {
        weeks {
          contributionDays {
            date
            contributionCount
            color
          }
        }
      }
    }
  }
}

Authentication uses a GitHub Personal Access Token.

2.4 Data Models
ContributionDay
Field	Type
date	String
contributionCount	Int
color	String
ContributionWeek

List of ContributionDay

ContributionCalendar

List of ContributionWeek

2.5 Rendering Engine

The renderer draws the heatmap grid using the Android Canvas.

Grid Structure

Columns → 53 weeks
Rows → 7 days

Each tile contains:

size

color

animation state

2.6 Live Wallpaper Service

The wallpaper runs inside:

WallpaperService
Components

WallpaperService

Wallpaper Engine

Renderer

Background Scheduler

Lifecycle
onCreate()
onSurfaceCreated()
onVisibilityChanged()
onSurfaceDestroyed()
onDestroy()
2.7 Background Sync

WorkManager schedules background data refresh tasks.

Recommended Interval

6 hours

Flow
Worker
  ↓
Fetch GitHub API
  ↓
Update Local Database
  ↓
Notify Renderer
2.8 Performance Strategy

Redraw only changed tiles

Hardware accelerated rendering

Cache parsed contribution data

Avoid continuous network polling

3. Design Document
3.1 Visual Concept

The wallpaper displays a floating grid of contribution tiles representing the current year.

Each tile glows slightly and varies in brightness depending on contribution activity.

The grid subtly animates to give the wallpaper a living feeling.

3.2 Layout
Grid Layout

53 columns (weeks)
7 rows (days)

Spacing

Small padding between tiles to mimic GitHub UI.

Tile Shape

Rounded squares.

3.3 Color Mapping

Contribution intensity determines tile color.

Contributions	Color
0	Dark gray
1-3	Light green
4-6	Medium green
7+	Bright green

Themes can override this palette.

3.4 Animation Design
Tile Update Animation

When contributions update:

scale animation

glow pulse

fade transition

Optional Particle Mode

Tiny particles appear when commits increase.

3.5 Theme System

Themes defined through a configuration model.

Example Themes

Classic GitHub

Neon Cyberpunk

Solarized Dark

Purple Dev Mode

Themes modify:

tile colors

background color

glow intensity

3.6 Settings Screen

User configurable settings:

GitHub username

GitHub API token

theme selection

animation toggle

update frequency

3.7 Performance Design
Rendering Strategy
Draw static background once
Update tiles only when contributions change
Target Frame Rate

30 FPS maximum.

4. Future Enhancements
3D Contribution Visualization

Render contributions as a 3D skyline where commit count controls cube height.

Developer Stats Mode

Display:

commit streak

total commits

yearly progress

Multiple GitHub Accounts

Support switching between different GitHub profiles.

Interactive Mode

Tap a day tile to open commit history.

End of Document

---

Small engineering observation worth keeping in mind. GitHub’s grid is deceptively simple: 371 tiny squares encode a year of behavior. Humans are very sensitive to visual streaks. When those squares appear every day on a device wallpaper, the brain starts treating empty squares like unfinished tasks. That psychological loop quietly nudges productivity.

The squares stop being pixels. They become a **calendar of effort**.