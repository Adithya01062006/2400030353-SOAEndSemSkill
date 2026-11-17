Write-Host "========================================================" -ForegroundColor Cyan
Write-Host " Testing Client-Side Load Balancing via API Gateway" -ForegroundColor Cyan
Write-Host " Gateway Endpoint: http://localhost:8080/api/token" -ForegroundColor Cyan
Write-Host "========================================================" -ForegroundColor Cyan

$requests = 6
for ($i = 1; $i -le $requests; $i++) {
    try {
        $response = Invoke-RestMethod -Uri "http://localhost:8080/api/token" -Method Get
        Write-Host "Request #$i Response:" -ForegroundColor Green
        Write-Host ($response | ConvertTo-Json -Compress)
    } catch {
        Write-Host "Request #$i Failed: $($_.Exception.Message)" -ForegroundColor Red
    }
    Start-Sleep -Milliseconds 800
}

Write-Host "`n========================================================" -ForegroundColor Cyan
Write-Host " Load Balancing Test Completed." -ForegroundColor Cyan
Write-Host "========================================================" -ForegroundColor Cyan
